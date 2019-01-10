package com.example.rviciana.safeflight.extensions

import android.content.Context
import com.example.rviciana.safeflight.SafeFlightsApplication

fun Context.asApplication(): SafeFlightsApplication
        = this.applicationContext as SafeFlightsApplication