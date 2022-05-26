package com.light.cbrconv.domain.usecase

import com.light.cbrconv.domain.entity.ConversionCurrency
import com.light.cbrconv.domain.entity.Currency
import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConversionCurrencyUseCaseImpl @Inject constructor(private val conversionCurrency: ConversionCurrency) : ConversionCurrencyUseCase {

    override fun conversionCurrency(currency: BigDecimal?, amountCurrency: BigDecimal?, selectCurrency: Currency): String =
        conversionCurrency.currencyTransactions(currency, amountCurrency, selectCurrency).toString()
}