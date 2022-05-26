package com.light.cbrconv.presentation

import com.light.cbrconv.domain.entity.Currency

sealed class AppState {

    data class Success(val listCurrency: List<Currency>) : AppState()
    data class SuccessListCharCode(val listCharCode: List<String>) : AppState()
    data class SuccessCurrentForCharCode(val listData: Currency) : AppState()
    data class SuccessConversionCurrency(val resultTransaction: String) : AppState()

    data class Error(val e: Throwable) : AppState()
}
