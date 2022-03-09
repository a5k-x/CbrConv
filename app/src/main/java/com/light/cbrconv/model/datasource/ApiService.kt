package com.light.cbrconv.model.datasource

import com.light.cbrconv.model.data.DataModel
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("daily_json.js")
    fun getData(): Call<DataModel>
}