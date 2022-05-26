package com.light.cbrconv.data.network

import com.light.cbrconv.data.datasource.ConvertDataModel
import com.light.cbrconv.data.model.CurrencyRemote
import com.light.cbrconv.domain.entity.Currency

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConversionRemoteModel @Inject constructor() : ConvertDataModel<CurrencyRemote> {

    override fun conversionToCurrent(dataModel: CurrencyRemote): Currency =
        Currency(
            id = dataModel.iD,
            numCode = dataModel.NumCode,
            charCode = dataModel.charCode,
            nominal = dataModel.nominal,
            value = dataModel.value,
            name = dataModel.name,
            previous = dataModel.previous
        )

    override fun conversionToDataModel(current: Currency): CurrencyRemote =
        CurrencyRemote(
            iD = current.id,
            NumCode = current.numCode,
            charCode = current.charCode,
            nominal = current.nominal,
            value = current.value,
            name = current.name,
            previous = current.previous
        )
}