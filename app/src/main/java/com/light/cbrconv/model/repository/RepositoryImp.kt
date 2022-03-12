package com.light.cbrconv.model.repository

import com.light.cbrconv.model.datasource.DataSource
import com.light.cbrconv.viewmodel.AppState

class RepositoryImp(private val datasource:DataSource<AppState>):IRepository<AppState> {
    override fun getData(): AppState {
        return datasource.getData()
    }



}