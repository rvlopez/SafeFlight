package com.example.rviciana.safeflight.view

import android.app.Activity
import com.example.rviciana.safeflight.domain.bo.Airport
import com.example.rviciana.safeflight.domain.bo.AirportsResponse
import com.example.rviciana.safeflight.view.form.FormActivity
import com.example.rviciana.safeflight.view.map.FlightsMapListActivity

class NavigatorImpl(private val activity: Activity) : Navigator{

    override fun navigateToFormActivity(airportsResponse: AirportsResponse) {
        activity.startActivity(FormActivity.getCallingIntent(activity, airportsResponse))
    }

    override fun navigateToFlightsListActivity(originAirport: Airport, destinationAirport: Airport) {
        activity.startActivity(
                FlightsMapListActivity.getCallingIntent(activity, originAirport, destinationAirport))
    }

    override fun navigateToMapActivity() {

    }
}