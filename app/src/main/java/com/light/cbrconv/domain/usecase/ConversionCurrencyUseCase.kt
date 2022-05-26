package com.light.cbrconv.domain.usecase

import com.light.cbrconv.domain.entity.Currency
import java.math.BigDecimal

interface ConversionCurrencyUseCase {

    fun conversionCurrency(currency: BigDecimal?, amountCurrency: BigDecimal?, selectCurrency: Currency): String
}