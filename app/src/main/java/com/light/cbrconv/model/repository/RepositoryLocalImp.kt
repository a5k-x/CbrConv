package com.light.cbrconv.model.repository

import com.light.cbrconv.App
import com.light.cbrconv.model.data.Aui
import com.light.cbrconv.model.datasource.DataSourceLocal
import com.light.cbrconv.model.datasource.IRoomDatabase
import com.light.cbrconv.viewmodel.AppState
import javax.inject.Inject

class RepositoryLocalImp() : IRepositoryLocal {

    init {
        App.instance.appComponent.inject(this)
    }
    @Inject lateinit var remote: DataSourceLocal

    override fun getCountDB(): Int {
        return remote.getCountDB()
    }

    override fun getInsertAll(list: List<Aui>) {
        return remote.getInsertAll(list)
    }

    override fun getListAllValute(): AppState {
        return remote.getListAllValute()
    }

    override fun updateAllList(itemList: List<Aui>) {
        return remote.updateAllList(itemList)
    }

    override fun getDataCharCode(charCode: String): AppState {
        return remote.getDataCharCode(charCode)
    }

    override fun getListCharCode(): AppState {
        return remote.getListCharCode()
    }


}