package com.light.cbrconv.model.repository

import com.light.cbrconv.model.data.DataModel
import com.light.cbrconv.model.datasource.DataSource

class RepositoryImp(private val datasource: DataSource<DataModel>) : IRepository<DataModel> {
    override suspend fun getData(): DataModel {
        return datasource.getData()
    }

}