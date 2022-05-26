package com.light.cbrconv.data.database

import androidx.room.*
import com.light.cbrconv.data.model.CurrencyDb


@Dao
interface CurrencyDao {

    @Query("SELECT count()  from currency_table")
    suspend fun countCurrency(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertListCurrency(entity: List<CurrencyDb>)

    @Query("SELECT * FROM currency_table")
    suspend fun getListCurrency(): List<CurrencyDb>?

    @Update(entity = CurrencyDb::class)
    suspend fun updateItem(item: CurrencyDb)

    @Query("SELECT * FROM currency_table WHERE charCode=:charCode")
    suspend fun searchCurrencyForCharCode(charCode: String): CurrencyDb?

    @Query("SELECT charCode FROM currency_table")
    suspend fun searchAllCharCode(): List<String>?
}