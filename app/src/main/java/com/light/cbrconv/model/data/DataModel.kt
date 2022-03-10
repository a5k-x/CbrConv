package com.light.cbrconv.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataModel(
    val timeStamp: String?,
    val valute: List<Aui>?
) : Parcelable

@Parcelize
data class Aui(
    val iD: String?,
    val NumCode: Int?,
    val charCode: String?,
    val nominal: Int?,
    private val value: Double?,
    val name: String?,
    val previous: Double?
) : Parcelable