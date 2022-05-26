package com.light.cbrconv.domain.entity

data class Currency(

    val id: String,
    val numCode: Int?,
    val charCode: String?,
    val nominal: Int?,
    val value: Double?,
    val name: String?,
    val previous: Double?

)
