package com.light.cbrconv.domain.usecase

import com.light.cbrconv.domain.entity.Currency

interface WorkingWithCurrencyUseCase {

    suspend fun getListCurrency(flag: Boolean): List<Currency>?

    //Вывод кол-ва строк
    suspend fun getCountDB(): Int

    //вставить / обновить список всех значений в БД
    suspend fun updateAllList(itemList: List<Currency>)

    //Поиск по CharCode
    suspend fun getCurrencyForCharCode(charCode: String): Currency?

    //Вывести список всех CharCode
    suspend fun getListCharCode(): List<String>?

}