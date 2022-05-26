package com.light.cbrconv.domain.entity

import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConversionCurrencyImpl @Inject constructor() : ConversionCurrency {

    override fun currencyTransactions( currency: BigDecimal?, amountCurrency: BigDecimal?, selectCurrency: Currency
    ): BigDecimal {

        val currencyValue = selectCurrency.value?.toBigDecimal()
        val currencyNominal = selectCurrency.nominal?.toBigDecimal()

        return if (currencyValue != null && currencyNominal != null && currency != null && amountCurrency != null) {
            (currency * amountCurrency).divide(
                currencyValue.divide(currencyNominal, 4, RoundingMode.HALF_UP),
                4,
                RoundingMode.HALF_UP
            )
        } else 0.toBigDecimal()

    }
}