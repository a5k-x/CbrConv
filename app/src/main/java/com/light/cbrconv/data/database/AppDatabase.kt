package com.light.cbrconv.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.light.cbrconv.data.model.CurrencyDb

import javax.inject.Singleton


@Database(entities = [CurrencyDb::class], version = 1, exportSchema = true)
@Singleton
abstract class AppDatabase : RoomDatabase() {

    abstract fun getDao(): CurrencyDao

    companion object {
        private var instance: AppDatabase? = null
        private const val DB_NAME = "cbrcurrency.db"

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(applicationContext: Context) =
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, DB_NAME)
                .build()
    }
}
