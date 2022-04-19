package com.light.cbrconv.model.datasource

import com.light.cbrconv.model.data.DataModel

class DataSourceRemote(private val remote:RetrofitImp = RetrofitImp()):DataSource<DataModel> {

    override suspend fun getData(): DataModel {
       return remote.getData()
    }
}