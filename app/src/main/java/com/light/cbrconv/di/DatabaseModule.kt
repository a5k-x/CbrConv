package com.light.cbrconv.di

import android.content.Context
import com.light.cbrconv.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(appContext: Context): AppDatabase = AppDatabase.getDatabase(appContext)

}

