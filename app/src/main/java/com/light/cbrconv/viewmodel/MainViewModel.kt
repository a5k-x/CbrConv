package com.light.cbrconv.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.light.cbrconv.App
import com.light.cbrconv.model.data.Aui
import com.light.cbrconv.model.data.DataModel
import com.light.cbrconv.model.datasource.DataSourceRemote
import com.light.cbrconv.model.repository.RepositoryImp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MainViewModel() : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO)
    private val db = App.instancedb?.getDao()
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

    fun getUpdate() {

        liveData.postValue(AppState.Loading(1))
        val data = RepositoryImp(DataSourceRemote()).getData()
        scope.launch {
            data.enqueue(object : Callback<DataModel> {
                override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                    liveData.postValue(response.body()?.let { AppState.Success(it) })
                    response.body()?.getValute()?.let { setDataModel(it) }
                }

                override fun onFailure(call: Call<DataModel>, t: Throwable) {
                    liveData.postValue(AppState.Error(t))
                }
            })
        }

    }

    //если БД пустая то запрос в сеть
    fun countItemDB(boolean: Boolean) {

        if (timer == null) {
            timer = Timer()
        }
        if (boolean) {
            timer?.schedule(object : TimerTask() {
                override fun run() {
                    Log.i("AAA", "Задача")
                    getUpdate()
                }
            }, 0L, 5000)
        } else {
            timer?.cancel()
            timer = null
            scope.launch {
                val countsItemDatabase: Int = db?.countCoulms()!!
                if (countsItemDatabase <= 0 || countsItemDatabase == null) {
                    //если нет данных отобразить ошибку
                    liveData.postValue(AppState.Error(Throwable("База данных пуста")))
                    getUpdate()
                } else {
                    //вывод данных на UI
                    liveData.postValue(AppState.SuccessAui(db?.getDataList()))
                }
            }
        }

    }

    //Добавить все значения в бд
    fun setDataModel(data: List<Aui>) {
        scope.launch {
            for (i in data.indices) {
                db?.insertAll(data[i])
            }
        }
    }

    //Обновление данных
    fun setUpdateDataModel(data: List<Aui>) {
        scope.launch {
            for (i in data.indices) {
                db?.updateItem(data[i])
            }
            Log.i("AAA", "Данные в БД должны записаться")
        }
    }
//Получить список валют из БД
    fun searchAllCharCode(){
        scope.launch {
            liveDataConvert.postValue(db?.searchAllCharCode()?.let { AppState.SuccessCharCode(it) })

        }
    }
    //Получить выбранный объект из БД
    fun searchAui(charcode:String){
        scope.launch {
            liveDataConvertAui.postValue(db?.searchValute(charcode)
                ?.let { AppState.SuccessAuiTest(it) })
        }
    }



    fun closeScope() {
        scope.cancel()
    }
}