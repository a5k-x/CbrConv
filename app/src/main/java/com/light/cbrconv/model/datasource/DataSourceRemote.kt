package com.light.cbrconv.model.datasource

import com.light.cbrconv.viewmodel.AppState

class DataSourceRemote(private val remote:RetrofitImp = RetrofitImp()):DataSource<AppState> {

    override fun getData(): AppState {
       return remote.getData()
    }
}