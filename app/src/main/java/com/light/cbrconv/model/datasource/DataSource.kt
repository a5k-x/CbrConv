package com.light.cbrconv.model.datasource

interface DataSource<T> {

   suspend fun getData():T

}