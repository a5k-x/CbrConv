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

}