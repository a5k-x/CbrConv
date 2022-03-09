package com.light.cbrconv.model.datasource

import com.light.cbrconv.model.data.DataModel
import retrofit2.Call

class RoomDataBaseIml:DataSource<DataModel> {
   //Реализация бд
    override fun getData(): Call<DataModel> {
        TODO("not implemented")
    }
}