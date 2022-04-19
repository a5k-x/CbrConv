package com.light.cbrconv.model.data

import java.math.BigDecimal

class CurrencyConvertation : ICurrencyConverter {

    override fun currencyTransactions(
        currency: BigDecimal?,
        amountCurrency: BigDecimal?,
        selectCurrency: Aui
    ): BigDecimal {
        val currencyValue = selectCurrency.value?.toBigDecimal()
        val currencyNominal = selectCurrency.nominal?.toBigDecimal()
        return if (currencyValue != null && currencyNominal != null && currency != null && amountCurrency != null) {
            currency * amountCurrency * currencyValue / currencyNominal
        } else 0.toBigDecimal()

    }
}