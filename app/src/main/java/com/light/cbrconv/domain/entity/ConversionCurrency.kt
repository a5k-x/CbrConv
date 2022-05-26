package com.light.cbrconv.domain.entity

import java.math.BigDecimal

interface ConversionCurrency {

    fun currencyTransactions( currency: BigDecimal?, amountCurrency: BigDecimal?, selectCurrency: Currency): BigDecimal

}