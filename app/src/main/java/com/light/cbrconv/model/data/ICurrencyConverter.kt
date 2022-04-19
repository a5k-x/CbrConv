package com.light.cbrconv.model.data

import com.light.cbrconv.model.data.Aui
import java.math.BigDecimal

interface ICurrencyConverter {
    fun currencyTransactions(currency:BigDecimal?, amountCurrency:BigDecimal?, selectCurrency: Aui):BigDecimal
}