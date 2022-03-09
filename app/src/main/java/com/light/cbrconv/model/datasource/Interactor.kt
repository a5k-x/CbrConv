package com.light.cbrconv.model.datasource

import retrofit2.Call

interface Interactor<T> {
    fun getData(checkBool: Boolean): Call<T>
}