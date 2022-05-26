package com.light.cbrconv.data.database


import com.light.cbrconv.data.datasource.ConvertDataModel
import com.light.cbrconv.data.model.CurrencyDb
import com.light.cbrconv.domain.entity.Currency
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConversionDbModel @Inject constructor() : ConvertDataModel<CurrencyDb> {

    override fun conversionToCurrent(dataModel: CurrencyDb): Currency =
        Currency(
            id = dataModel.id,
            numCode = dataModel.numCode,
            charCode = dataModel.charCode,
            nominal = dataModel.nominal,
            value = dataModel.value,
            name = dataModel.name,
            previous = dataModel.previous
        )

    override fun conversionToDataModel(current: Currency): CurrencyDb =
        CurrencyDb(
            id = current.id,
            numCode = current.numCode,
            charCode = current.charCode,
            nominal = current.nominal,
            value = current.value,
            name = current.name,
            previous = current.previous
        )

}