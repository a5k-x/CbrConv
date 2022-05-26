package com.light.cbrconv.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "currency_table")
@Parcelize
data class CurrencyDb(
    @field:PrimaryKey val id: String,
    @field:ColumnInfo(name = "NumCode") val numCode: Int?,
    @field:ColumnInfo(name = "charCode") val charCode: String?,
    @field:ColumnInfo(name = "nominal") val nominal: Int?,
    @field:ColumnInfo(name = "value") val value: Double?,
    @field:ColumnInfo(name = "name") val name: String?,
    @field:ColumnInfo(name = "previous") val previous: Double?
) : Parcelable