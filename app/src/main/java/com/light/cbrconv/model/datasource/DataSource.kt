package com.light.cbrconv.model.datasource

interface DataSource<T> {

   fun getData():T

}