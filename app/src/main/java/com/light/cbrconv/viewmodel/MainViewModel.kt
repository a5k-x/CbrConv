package com.light.cbrconv.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.light.cbrconv.model.data.DataModel
import com.light.cbrconv.model.datasource.DataSourceLocal
import com.light.cbrconv.model.datasource.DataSourceRemote
import com.light.cbrconv.model.datasource.MainInteractor
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

    private var liveData = MutableLiveData<AppState>()

    fun getLiveData(): LiveData<AppState> {
        return liveData
    }

    fun getUpdate() {
        liveData.postValue(AppState.Loading(1))
        val data = MainInteractor(
            RepositoryImp(DataSourceRemote()),
            RepositoryImp(DataSourceLocal())
        ).getData(true)

        scope.launch {
            data.enqueue(object : Callback<DataModel> {
                override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                  liveData.postValue( response.body()?.let { AppState.Success(it) })
                }
                override fun onFailure(call: Call<DataModel>, t: Throwable) {
                    liveData.postValue(AppState.Error(t))
                }
            })
        }
    }

    fun closeScope() {
        scope.cancel()
    }
}