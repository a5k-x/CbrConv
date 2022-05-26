package com.light.cbrconv

import android.app.Application
import com.light.cbrconv.di.AppComponent
import com.light.cbrconv.di.DaggerAppComponent


class App:Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory()
            .create(applicationContext)
    }

}