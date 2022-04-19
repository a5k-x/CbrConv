package com.light.cbrconv.model.datasource

import com.light.cbrconv.App
import com.light.cbrconv.model.data.Aui
import com.light.cbrconv.viewmodel.AppState
import javax.inject.Inject

class DataSourceLocal():IRoomDatabase {

    init {
        App.instance.appComponent.inject(this)
    }

    @Inject lateinit var roomDatabaseIml: IRoomDatabase

    override fun getCountDB(): Int {
       return roomDatabaseIml.getCountDB()
    }

    override fun getInsertAll(list: List<Aui>) {
       return roomDatabaseIml.getInsertAll(list)
    }

    override fun getListAllValute(): AppState {
        return roomDatabaseIml.getListAllValute()
    }

    override fun updateAllList(itemList: List<Aui>) {
       return roomDatabaseIml.updateAllList(itemList)
    }

    override fun getDataCharCode(charCode: String): AppState {
        return roomDatabaseIml.getDataCharCode(charCode)
    }

    override fun getListCharCode(): AppState {
       return roomDatabaseIml.getListCharCode()
    }

}