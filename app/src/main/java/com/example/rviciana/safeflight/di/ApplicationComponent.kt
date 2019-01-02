package com.example.rviciana.safeflight.di

import com.example.rviciana.safeflight.SafeFlightsApplication
import com.example.rviciana.safeflight.view.form.di.FormComponent
import com.example.rviciana.safeflight.view.form.di.FormModule
import com.example.rviciana.safeflight.view.map.di.FlightsComponent
import com.example.rviciana.safeflight.view.map.di.FlightsModule
import com.example.rviciana.safeflight.view.oauth.di.OAuthComponent
import com.example.rviciana.safeflight.view.oauth.di.OAuthModule
import dagger.Component

@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {

    fun inject(application: SafeFlightsApplication)

    fun plus(oAuthModule: OAuthModule): OAuthComponent

    fun plus(formModule: FormModule): FormComponent

    fun plus(flightsModule: FlightsModule) : FlightsComponent
}