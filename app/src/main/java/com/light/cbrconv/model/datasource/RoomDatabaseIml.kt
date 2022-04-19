package com.light.cbrconv.model.datasource

import android.util.Log
import com.light.cbrconv.App
import com.light.cbrconv.model.data.Aui
import com.light.cbrconv.viewmodel.AppState
import javax.inject.Inject

class RoomDatabaseIml : IRoomDatabase {

    init {
        App.instance.appComponent.inject(this)
    }

    @Inject lateinit var db: HistoryDao

    //Получить кол-во строк в БД
    override fun getCountDB(): Int {
        return db.countCoulms()
    }

    //Вставка данных
    override fun getInsertAll(list: List<Aui>) {
        try {
            for (i in list.indices) {
                db.insertAll(list[i])
            }
        } catch (e: Exception) {
            Log.e("InsertError", "Ошибка вставки данных в БД ${e.message}")
        }
    }

    //Получить список валют
    override fun getListAllValute(): AppState {
        val dataList = db.getDataList()
        if (!dataList.isNullOrEmpty()) {
            return AppState.SuccessAui(dataList)
        } else return AppState.Error(Throwable("Нет данных"))
    }

    override fun updateAllList(itemList: List<Aui>) {
        try {
            if (getCountDB()>0){
            for (i in itemList.indices) {
                db.updateItem(itemList[i])
            }} else {
                getInsertAll(itemList)
            }
        } catch (e: Exception) {
            Log.e("InsertError", "Ошибка обновления данных в БД ${e.message}")
        }
    }

    //Получение валюты из бд
    override fun getDataCharCode(charCode: String): AppState {
        val dataCharCode = db.searchValute(charCode)
        return AppState.SuccessAuiTest(dataCharCode)
    }

    //Получить список CharCode из БД
    override fun getListCharCode(): AppState {
        val data = db.searchAllCharCode()
        if (!data.isNullOrEmpty()) {
            return AppState.SuccessCharCode(data)
        } else return AppState.Error(Throwable("Нет дынных"))
    }

}