package com.example.rviciana.safeflight.config

import androidx.test.platform.app.InstrumentationRegistry
import com.example.rviciana.safeflight.SafeFlightsApplication
import com.example.rviciana.safeflight.di.ApplicationComponent
import com.example.rviciana.safeflight.di.ApplicationModule
import com.example.rviciana.safeflight.di.NetworkModule
import com.example.rviciana.safeflight.extensions.asApplication
import it.cosenonjaviste.daggermock.DaggerMock
import it.cosenonjaviste.daggermock.DaggerMockRule

fun getDaggerRule(): DaggerMockRule<ApplicationComponent> =
        DaggerMock.rule(ApplicationModule(app.applicationContext), NetworkModule()) {
            set { component -> app.component = component }
        }

val app: SafeFlightsApplication =
        InstrumentationRegistry.getInstrumentation().targetContext.asApplication()

