package com.light.cbrconv.model.datasource

import com.light.cbrconv.App
import com.light.cbrconv.model.data.DataModel
import javax.inject.Inject

class DataSourceRemote:DataSource<DataModel> {

    init {
        App.instance.appComponent.inject(this)
    }

    @Inject lateinit var remote: RetrofitImp

    override suspend fun getData(): DataModel = remote.getData()

}