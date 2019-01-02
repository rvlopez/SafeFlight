package com.example.rviciana.safeflight.di

import android.content.Context
import com.example.rviciana.safeflight.SafeFlightsApplication
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
class ApplicationModule {

    @Provides
    fun context(application: SafeFlightsApplication): Context = application.applicationContext

    @Provides
    @Named("observeOn")
    fun observeOnScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Named("subscribeOn")
    fun subscribeOnScheduler(): Scheduler = Schedulers.io()
}