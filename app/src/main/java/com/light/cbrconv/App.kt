package com.light.cbrconv

import android.app.Application
import androidx.room.Room
import com.light.cbrconv.di.AppComponent
import com.light.cbrconv.di.AppModule
import com.light.cbrconv.di.DaggerAppComponent


import com.light.cbrconv.model.datasource.AppDatabase
import com.light.cbrconv.model.datasource.RetrofitImp


class App:Application() {
    companion object{
        lateinit var instance:App

       /* private const val DB_NAME = "cbrvalute.db"
        var instancedb: AppDatabase? = null
         fun getInstance() = instancedb
             ?: throw RuntimeException("Database has not been created. Please call create(context)")*/

    }
    lateinit var appComponent:AppComponent
    override fun onCreate() {
        super.onCreate()
        instance = this
       /* if (instancedb == null){
            instancedb = Room.databaseBuilder(applicationContext,AppDatabase::class.java, DB_NAME)
                .build()
        }*/
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    }
}