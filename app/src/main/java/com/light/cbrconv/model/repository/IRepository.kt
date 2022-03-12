package com.light.cbrconv.model.repository


interface IRepository<T> {
    fun getData():T

}