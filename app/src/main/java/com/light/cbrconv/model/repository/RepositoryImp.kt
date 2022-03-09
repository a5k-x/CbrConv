package com.light.cbrconv.model.repository

import com.light.cbrconv.model.data.DataModel
import com.light.cbrconv.model.datasource.DataSource
import retrofit2.Call

class RepositoryImp(private val datasource:DataSource<DataModel>):Repository<DataModel> {
    override fun getData(): Call<DataModel> {
        return datasource.getData()
    }
}