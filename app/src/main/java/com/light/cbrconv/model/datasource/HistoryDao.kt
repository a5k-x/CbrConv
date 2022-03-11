package com.light.cbrconv.model.datasource

import androidx.room.*
import com.light.cbrconv.model.data.Aui
import com.light.cbrconv.model.data.DataModel
import retrofit2.Call

@Dao
interface HistoryDao {

    @Query("SELECT count()  from VALUTE")
    fun countCoulms():Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(entity: Aui)

    @Query("SELECT * FROM VALUTE")
    fun getDataList():List<Aui>

    @Update(entity = Aui::class)
    fun updateItem(item:Aui)

    @Query("SELECT * FROM VALUTE WHERE charCode=:charCode")
    fun searchValute(charCode:String):Aui

    @Query("SELECT charCode FROM VALUTE")
    fun searchAllCharCode():List<String>
}