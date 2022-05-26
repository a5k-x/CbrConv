package com.light.cbrconv.data.model

data class CurrencyRemoteModel(
    val valute: List<CurrencyRemote>?
)

data class CurrencyRemote(
    val iD: String,
    val NumCode: Int?,
    val charCode: String?,
    val nominal: Int?,
    val value: Double?,
    val name: String?,
    val previous: Double?
)

