package com.light.cbrconv.model.repository

import com.light.cbrconv.model.data.Aui
import com.light.cbrconv.model.datasource.IRoomDatabase
import com.light.cbrconv.viewmodel.AppState

class RepositoryLocalImp(private val remote: IRoomDatabase) : IRepositoryLocal {

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