package com.light.cbrconv.model.datasource

import com.light.cbrconv.model.data.DataModel
import retrofit2.Call

class DataSourceLocal(private val remote: RoomDataBaseIml = RoomDataBaseIml()) :DataSource<DataModel> {

    override fun getData(): Call<DataModel> {
       return remote.getData()
    }
}