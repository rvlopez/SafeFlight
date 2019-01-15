package com.example.rviciana.safeflight.view.form

import com.example.rviciana.safeflight.domain.bo.Airport

interface FormContract {

    interface View {
        fun navigateToFlightsListActivity(originAirport: Airport, destinationAirport: Airport)
        fun showOriginInputError()
        fun showDestinationInputError()
        fun hideOriginInputError()
        fun hideDestinationInputError()
    }

    interface Presenter {
        fun setView(view: FormContract.View)
        fun onAirportsReady(originAirport: Airport, destinationAirport: Airport)
        fun onOriginInputError()
        fun onDestinationInputError()
    }
}