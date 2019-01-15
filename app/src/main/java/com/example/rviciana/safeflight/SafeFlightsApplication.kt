package com.example.rviciana.safeflight

import android.app.Application
import com.example.rviciana.safeflight.di.ApplicationComponent
import com.example.rviciana.safeflight.di.ApplicationModule
import com.example.rviciana.safeflight.di.DaggerApplicationComponent

class SafeFlightsApplication : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initializeInjector()
    }

    private fun initializeInjector() {
        component = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(applicationContext))
                .build()

        component.inject(this)
    }

}