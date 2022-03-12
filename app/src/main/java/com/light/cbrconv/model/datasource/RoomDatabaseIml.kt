package com.light.cbrconv.model.datasource

import android.util.Log
import com.light.cbrconv.App
import com.light.cbrconv.model.data.Aui
import com.light.cbrconv.viewmodel.AppState

class RoomDatabaseIml : IRoomDatabase {

    private val db = App.instancedb?.getDao()

    //Получить кол-во строк в БД
    override fun getCountDB(): Int {
        var countDB = db?.countCoulms()
        if (countDB != null) {
            return countDB
        } else return 0
    }

    //Вставка данных
    override fun getInsertAll(list: List<Aui>) {
        try {
            for (i in list.indices) {
                db?.insertAll(list[i])
            }
        } catch (e: Exception) {
            Log.e("InsertError", "Ошибка вставки данных в БД ${e.message}")
        }
    }

    //Получить список валют
    override fun getListAllValute(): AppState {
        var dataList = db?.getDataList()
        if (!dataList.isNullOrEmpty()) {
            return AppState.SuccessAui(dataList)
        } else return AppState.Error(Throwable("Нет данных"))
    }

    override fun updateAllList(itemlist: List<Aui>) {
        try {
            if (getCountDB()>0){
            for (i in itemlist.indices) {
                db?.updateItem(itemlist[i])
            }} else {
                getInsertAll(itemlist)
            }
        } catch (e: Exception) {
            Log.e("InsertError", "Ошибка обновления данных в БД ${e.message}")
        }
    }

    //Получение валюты из бд
    override fun getDataCharCode(charCode: String): AppState {
        val dataCharCode = db?.searchValute(charCode)
        if (dataCharCode != null) {
            return AppState.SuccessAuiTest(dataCharCode)
        } else
            return AppState.Error(Throwable("Нет данных в бд"))
    }

    //Получить список CharCode из БД
    override fun getListCharCode(): AppState {
        var data = db?.searchAllCharCode()
        if (!data.isNullOrEmpty()) {
            return AppState.SuccessCharCode(data)
        } else return AppState.Error(Throwable("Нет дынных"))
    }

}