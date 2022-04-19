package com.light.cbrconv.di

import androidx.room.Room
import com.light.cbrconv.App
import com.light.cbrconv.model.datasource.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    var instancedb: AppDatabase? = null

    @Singleton
    @Provides
    fun getDbName():String = "cbrvalute.db"

    @Provides
    fun dataBase(app:App, namedb:String):AppDatabase = Room.databaseBuilder(app,AppDatabase::class.java, namedb)
        .build()

    @Provides
    fun getDao(appDatabase: AppDatabase):HistoryDao = appDatabase.getDao()

    @Provides
    fun getRoomDbImpl():IRoomDatabase = RoomDatabaseIml()

    @Provides
    fun getRoomDblocalImpl():DataSourceLocal = DataSourceLocal()
}