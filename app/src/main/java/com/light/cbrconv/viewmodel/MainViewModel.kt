package com.light.cbrconv.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

class MainViewModel : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO)
    private val db = App.instancedb?.getDao()
    private var liveData = MutableLiveData<AppState>()

    private var listDataModel:List<Aui>? = null

    fun getLiveData(): LiveData<AppState> {
        return liveData
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
    fun countItemDB(boolean: Boolean){
     liveData.postValue(AppState.Loading(1))
     if (boolean) {
    getUpdate()
     } else {
         scope.launch {
             val countsItemDatabase:Int = db?.countCoulms()!!
             if (countsItemDatabase <= 0 || countsItemDatabase==null){
                //если нет данных отобразить ошибку
                 liveData.postValue(AppState.Error(Throwable("База данных пуста")))
             } else {
                //вывод данных на UI
                 liveData.postValue(AppState.SuccessAui(db?.getDataList()))
             }
         }
     }

    }
//Добавить все значения в бд
    fun setDataModel(data:List<Aui>){
        scope.launch {
            for (i in data.indices){
                db?.insertAll(data[i])
            }

        }
    }

//Обновление данных
  fun setUpdateDataModel(data:List<Aui>){
        scope.launch {
            for (i in data.indices){
                db?.updateItem(data[i])
            }
            Log.i("AAA", "Данные в БД должны записаться")
        }
    }

    fun closeScope() {
        scope.cancel()
    }
}