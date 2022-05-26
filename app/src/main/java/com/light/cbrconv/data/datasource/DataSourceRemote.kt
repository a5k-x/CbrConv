package com.light.cbrconv.data.datasource

interface DataSourceRemote<T> {

    //get list currency
    suspend fun getListCurrency(): List<T>?

}