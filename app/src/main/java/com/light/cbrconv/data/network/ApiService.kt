package com.light.cbrconv.data.network

import com.light.cbrconv.data.model.CurrencyRemoteModel
import retrofit2.http.GET


interface ApiService {

    @GET("daily_json.js")
    suspend fun getData(): CurrencyRemoteModel

}