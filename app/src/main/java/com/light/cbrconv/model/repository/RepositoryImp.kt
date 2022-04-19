package com.light.cbrconv.model.repository

import com.light.cbrconv.App
import com.light.cbrconv.model.data.DataModel
import com.light.cbrconv.model.datasource.DataSource
import javax.inject.Inject

class RepositoryImp() : IRepository<DataModel> {

    init {
        App.instance.appComponent.inject(this)
    }

    @Inject lateinit var datasource: DataSource<DataModel>

    override suspend fun getData(): DataModel {
        return datasource.getData()
    }

}