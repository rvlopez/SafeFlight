package com.example.rviciana.safeflight.view.form

import com.example.rviciana.safeflight.domain.bo.Airport

class FormPresenter(): FormContract.Presenter {

    internal lateinit var view: FormContract.View

    override fun setView(view: FormContract.View) {
        this.view = view
    }

    override fun onAirportsReady(originAirport: Airport, destinationAirport: Airport) {
        view.navigateToFlightsListActivity(originAirport, destinationAirport)
    }
}