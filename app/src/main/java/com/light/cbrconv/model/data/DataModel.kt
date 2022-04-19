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
     val valute: List<Aui>?
) : Parcelable

@Entity(tableName = "VALUTE")
@Parcelize
data class Aui(
    @field:PrimaryKey val iD: String,
    @field:ColumnInfo(name = "NumCode") val NumCode: Int?,
    @field:ColumnInfo(name = "charCode")  val charCode: String?,
    @field:ColumnInfo(name = "nominal")  val nominal: Int?,
    @field:ColumnInfo(name = "value")   val value: Double?,
    @field:ColumnInfo(name = "name")  val name: String?,
    @field:ColumnInfo(name = "previous")  val previous: Double?
) : Parcelable