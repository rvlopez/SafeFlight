package com.example.rviciana.safeflight.view

import com.example.rviciana.safeflight.domain.bo.Airport
import com.example.rviciana.safeflight.domain.bo.AirportsResponse

interface Navigator {

    fun navigateToFormActivity(airportsResponse: AirportsResponse)

    fun navigateToFlightsListActivity(originAirport: Airport, destinationAirport: Airport)

    fun navigateToMapActivity()
}