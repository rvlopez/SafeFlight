package com.example.rviciana.safeflight.view.form

import com.example.rviciana.safeflight.domain.bo.Airport

class FormPresenter: FormContract.Presenter {

    internal lateinit var view: FormContract.View

    override fun setView(view: FormContract.View) {
        this.view = view
    }

    override fun onAirportsReady(originAirport: Airport, destinationAirport: Airport) {
        view.hideOriginInputError()
        view.hideDestinationInputError()
        view.navigateToFlightsListActivity(originAirport, destinationAirport)
    }

    override fun onOriginInputError() {
        view.showOriginInputError()
    }

    override fun onDestinationInputError() {
        view.showDestinationInputError()
    }

    override fun onOriginEraserClicked() {
        view.clearSearchViewOrigin()
    }

    override fun onDestinationEraserClicked() {
        view.clearSearchViewDestination()
    }
}