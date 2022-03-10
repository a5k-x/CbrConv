package com.light.cbrconv.model.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.light.cbrconv.model.data.Aui

@Database(entities = [Aui::class], version = 1, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDao(): HistoryDao
}