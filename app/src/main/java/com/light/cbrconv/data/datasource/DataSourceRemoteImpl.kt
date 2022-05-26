package com.light.cbrconv.data.datasource


import com.light.cbrconv.data.model.CurrencyRemote
import com.light.cbrconv.data.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataSourceRemoteImpl @Inject constructor(private val apiService: ApiService) :
    DataSourceRemote<CurrencyRemote> {

    override suspend fun getListCurrency(): List<CurrencyRemote>? {
        return apiService.getData().valute
    }
}