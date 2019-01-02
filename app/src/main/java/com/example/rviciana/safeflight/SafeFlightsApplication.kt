package com.example.rviciana.safeflight

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.rviciana.safeflight.di.ApplicationComponent
import com.example.rviciana.safeflight.di.ApplicationModule
import com.example.rviciana.safeflight.di.DaggerApplicationComponent

class SafeFlightsApplication : Application() {

    lateinit var component: ApplicationComponent
    lateinit var sharedPreferences: SharedPreferences

    companion object {
        const val SHARED = "SHARED"
    }

    override fun onCreate() {
        super.onCreate()
        initializeInjector()
        initSharedPreferences()
    }

    private fun initializeInjector() {
        component = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule())
                .build()

        component.inject(this)
    }

    private fun initSharedPreferences() {
        sharedPreferences = this.getSharedPreferences(SHARED, Context.MODE_PRIVATE)
    }
}