package com.light.cbrconv.data.repository

import com.light.cbrconv.data.datasource.ConvertDataModel
import com.light.cbrconv.data.datasource.DataSourceLocal
import com.light.cbrconv.data.datasource.DataSourceRemote
import com.light.cbrconv.data.model.CurrencyDb
import com.light.cbrconv.data.model.CurrencyRemote
import com.light.cbrconv.domain.entity.Currency
import com.light.cbrconv.domain.repository.Repository

import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val dataSourceLocal: DataSourceLocal<CurrencyDb>,
    private val dataSourceRemote: DataSourceRemote<CurrencyRemote>,
    private val conversionDbModel: ConvertDataModel<CurrencyDb>,
    private val conversionRemoteModel: ConvertDataModel<CurrencyRemote>
) : Repository {

    override suspend fun getListCurrency(flag: Boolean): List<Currency>? =
        if (flag) {
            val listDataModel = dataSourceRemote.getListCurrency()
            listDataModel?.map {
                conversionRemoteModel.conversionToCurrent(it)
            }
        } else {
            val listDataModel = dataSourceLocal.getListCurrency()
            listDataModel?.map { conversionDbModel.conversionToCurrent(it) }
        }

    override suspend fun getCountDB(): Int {
        return dataSourceLocal.getCountDB()
    }

    override suspend fun updateAllList(itemList: List<Currency>) {
        val convertCurrency = itemList.map { conversionDbModel.conversionToDataModel(it) }
        dataSourceLocal.updateListCurrency(convertCurrency)
    }

    override suspend fun getCurrencyForCharCode(charCode: String): Currency? {
        val currencyDatabase = dataSourceLocal.searchCurrencyForCharCode(charCode)
        return currencyDatabase?.let { conversionDbModel.conversionToCurrent(it) }
    }

    override suspend fun getListCharCode(): List<String>? {
        return dataSourceLocal.getListCharCode()
    }


}