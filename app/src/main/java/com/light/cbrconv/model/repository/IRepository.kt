package com.light.cbrconv.model.repository


interface IRepository<T> {
    suspend fun getData():T
}