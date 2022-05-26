package com.light.cbrconv.data.datasource

import com.light.cbrconv.domain.entity.Currency


interface ConvertDataModel<T> {

    fun conversionToCurrent(dataModel: T): Currency

    fun conversionToDataModel(current: Currency): T
}