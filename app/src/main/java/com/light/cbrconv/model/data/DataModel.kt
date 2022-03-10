package com.light.cbrconv.model.data

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize

class DataModel(
     private val timeStamp: String?,
     private val valute: List<Aui>?
) : Parcelable {
    fun gettimeStamp() = timeStamp
    fun getValute() = valute
}


@Entity(tableName = "VALUTE")
@Parcelize
data class Aui(
    @field:PrimaryKey val iD: String,
    @field:ColumnInfo(name = "NumCode") val NumCode: Int?,
    @field:ColumnInfo(name = "charCode")  val charCode: String?,
    @field:ColumnInfo(name = "nominal") private val nominal: Int?,
    @field:ColumnInfo(name = "value") private  val value: Double?,
    @field:ColumnInfo(name = "name") private val name: String?,
    @field:ColumnInfo(name = "previous")  val previous: Double?
) : Parcelable {

    fun getChatrCode() = charCode
    fun getNominal() = nominal
    fun getValue() = value
    fun getName() = name

}