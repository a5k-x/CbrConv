package com.light.cbrconv.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class DataModel(
    private val timeStamp: String?,
    private val valute: List<Aui>?
) : Parcelable {
    fun gettimeStamp() = timeStamp
    fun getValute() = valute
}

@Parcelize
data class Aui(
    private val iD: String?,
    private val NumCode: Int?,
    private val charCode: String?,
    private val nominal: Int?,
    private val value: Double?,
    private val name: String?,
    private val previous: Double?
) : Parcelable {
    fun getChatrCode() = charCode
    fun getNominal() = nominal
    fun getvalue() = value
    fun getName() = name

}