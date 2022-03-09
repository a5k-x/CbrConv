package com.light.cbrconv.model.repository


import com.light.cbrconv.model.data.DataModel
import retrofit2.Call

interface Repository<T> {
    fun getData():Call<T>
}