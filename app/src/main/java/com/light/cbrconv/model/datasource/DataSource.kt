package com.light.cbrconv.model.datasource

import com.light.cbrconv.model.data.DataModel
import retrofit2.Call

interface DataSource<T> {
    fun getData():Call<T>
}