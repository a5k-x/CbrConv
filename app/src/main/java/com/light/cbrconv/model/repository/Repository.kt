package com.light.cbrconv.model.repository


import retrofit2.Call

interface Repository<T> {
    fun getData():Call<T>

}