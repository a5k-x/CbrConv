package com.light.cbrconv.model.datasource

import com.light.cbrconv.model.data.DataModel
import com.light.cbrconv.viewmodel.AppState
import retrofit2.Call

class DataSourceRemote(private val remote:RetrofitImp = RetrofitImp()):DataSource<DataModel> {
    override fun getData(): Call<DataModel> {
       return remote.getData()
    }
}