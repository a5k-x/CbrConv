package com.light.cbrconv.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.light.cbrconv.model.data.Aui
import com.light.cbrconv.model.datasource.DataSourceLocal
import com.light.cbrconv.model.datasource.DataSourceRemote
import com.light.cbrconv.model.repository.RepositoryImp
import com.light.cbrconv.model.repository.RepositoryLocalImp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.*


class MainViewModel() : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO)
    private var liveData = MutableLiveData<AppState>()
    private var liveDataConvert = MutableLiveData<AppState>()
    private var liveDataConvertAui = MutableLiveData<AppState>()
    private var timer: Timer? = null

    fun getLiveData(): LiveData<AppState> {
        return liveData
    }

    fun getLiveDataConvert(): LiveData<AppState> {
        return liveDataConvert
    }

    fun getLiveDataConvertAui(): LiveData<AppState> {
        return liveDataConvertAui
    }

    //Получить данные из сети
    fun getUpdate() {
        liveData.postValue(AppState.Loading(1))
        scope.launch {
            val data = RepositoryImp(DataSourceRemote()).getData()
            liveData.postValue(data)
            Log.i("AAA", "Имя потока выполнения запроса в сеть - ${Thread.currentThread().name}")
        }


    }


    //Задать интервал запросов если БД пустая то запрос в сеть
    fun countItemDB(boolean: Boolean) {
        if (timer == null) {
            timer = Timer()
        }
        //если true то выполняем запрос с интервалом
        if (boolean) {
            timer?.schedule(object : TimerTask() {
                override fun run() {
                    Log.i("AAA", "Задача TimerTask")
                    getUpdate()
                }
            }, 0L, 15000)
        } else {
            timer?.cancel()
            timer = null
            scope.launch {
                //Если false то делаем запрос в БД, если она пуста то пытаемся взять данные из сети
                val countsItemDatabase: Int = RepositoryLocalImp(DataSourceLocal()).getCountDB()
                if (countsItemDatabase <= 0 || countsItemDatabase == null) {
                    //если нет данных отобразить ошибку
                    liveData.postValue(AppState.Error(Throwable("База данных пуста")))
                    getUpdate()

                } else {
                    //если бд не пуста, то вывод всех данных c бд
                    liveData.postValue(RepositoryLocalImp(DataSourceLocal()).getListAllValute())
                }
            }
        }

    }

    //Обновление данных в БД
    fun setUpdateDataModel(data: List<Aui>) {
        scope.launch {
            RepositoryLocalImp(DataSourceLocal()).updateAllList(data)
        }
    }

    //Получить список валют из БД
    fun searchAllCharCode() {
        scope.launch {
            liveDataConvert.postValue(RepositoryLocalImp(DataSourceLocal()).getListCharCode())
        }
    }

    //Получить выбранный объект из БД
    fun searchAui(charcode: String) {
        scope.launch {
            liveDataConvertAui.postValue(
                RepositoryLocalImp(DataSourceLocal()).getDataCharCode(
                    charcode
                )
            )
        }
    }


    fun closeScope() {
        scope.cancel()
    }
}