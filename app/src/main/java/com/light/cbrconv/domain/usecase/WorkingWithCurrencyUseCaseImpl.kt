package com.light.cbrconv.domain.usecase

import com.light.cbrconv.domain.entity.Currency
import com.light.cbrconv.domain.repository.Repository
import javax.inject.Inject

class WorkingWithCurrencyUseCaseImpl @Inject constructor(private val repository: Repository) : WorkingWithCurrencyUseCase {

    override suspend fun getListCurrency(flag: Boolean): List<Currency>? =
        repository.getListCurrency(flag)

    override suspend fun getCountDB(): Int = repository.getCountDB()

    override suspend fun updateAllList(itemList: List<Currency>) {
        repository.updateAllList(itemList)
    }

    override suspend fun getCurrencyForCharCode(charCode: String): Currency? =
        repository.getCurrencyForCharCode(charCode)

    override suspend fun getListCharCode(): List<String>? = repository.getListCharCode()

}