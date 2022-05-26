package com.light.cbrconv.data.datasource

import android.util.Log
import com.light.cbrconv.data.database.AppDatabase
import com.light.cbrconv.data.model.CurrencyDb
import java.sql.SQLException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataSourceLocalImpl @Inject constructor(appDatabase: AppDatabase) :
    DataSourceLocal<CurrencyDb> {

    private val db = appDatabase.getDao()

    //Get the number of rows in the database
    override suspend fun getCountDB(): Int = db.countCurrency() ?: 0

    //Получить список валют
    override suspend fun getListCurrency(): List<CurrencyDb>? = db.getListCurrency()

    //insert / update list currency
    override suspend fun updateListCurrency(itemList: List<CurrencyDb>) {
        try {
            if (getCountDB() > 0) {
                for (i in itemList.indices) {
                    db.updateItem(itemList[i])
                }
            } else {
                insertListCurrency(itemList)
            }
        } catch (e: SQLException) {
            Log.e("InsertError", "Database update error ${e.message}")
        }
    }

    //Inserting list currency
    private suspend fun insertListCurrency(listCurrency: List<CurrencyDb>?) {
        if (listCurrency != null) db.insertListCurrency(listCurrency)
    }

    //Получение валюты из бд
    override suspend fun searchCurrencyForCharCode(charCode: String): CurrencyDb? =
        db.searchCurrencyForCharCode(charCode)

    //Получить список CharCode из БД
    override suspend fun getListCharCode(): List<String>? = db.searchAllCharCode()

}
