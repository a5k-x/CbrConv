package com.light.cbrconv.model.datasource

import retrofit2.Call

interface DataSource<T> {
    fun getData():Call<T>
}