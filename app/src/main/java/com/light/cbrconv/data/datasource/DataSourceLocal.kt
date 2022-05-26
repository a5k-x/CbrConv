package com.light.cbrconv.data.datasource

interface DataSourceLocal<T> {

    //кол-во строк
    suspend fun getCountDB(): Int

    //Вставка / обновление всех значений
    suspend fun updateListCurrency(itemList: List<T>)

    //Получить список всех значений
    suspend fun getListCurrency(): List<T>?

    //Поиск по CharCode
    suspend fun searchCurrencyForCharCode(charCode: String): T?

    //Вывести список всех CharCode
    suspend fun getListCharCode(): List<String>?

}