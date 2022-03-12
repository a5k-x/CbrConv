package com.light.cbrconv.model.datasource

import com.light.cbrconv.model.data.Aui
import com.light.cbrconv.viewmodel.AppState

interface IRoomDatabase {

    //кол-во строк
    fun getCountDB():Int
    //Вставка всех значений
    fun getInsertAll(list: List<Aui>)
    //Получить список всех значений
    fun getListAllValute():AppState
    //обновить список всех значений в БД
    fun updateAllList(itemList:List<Aui>)
    //Поиск по CharCode
    fun getDataCharCode(charCode:String):AppState
    //Вывести список всех CharCode
    fun getListCharCode():AppState
}