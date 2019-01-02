package com.example.rviciana.safeflight.view.map.di

import com.example.rviciana.safeflight.view.map.FlightsMapListActivity
import dagger.Subcomponent

@Subcomponent(modules = [FlightsModule::class])
interface FlightsComponent {

    fun inject(flightsMapListActivity: FlightsMapListActivity)
}