package com.example.rviciana.safeflight.view.map

import com.example.rviciana.safeflight.domain.bo.ScheduleResource
import com.example.rviciana.safeflight.domain.usecase.FlightsUseCase

class FlightsListPresenter(private val flightsUseCase: FlightsUseCase
) : FlightsListContract.Presenter {

    internal lateinit var view: FlightsListContract.View

    override fun setView(view: FlightsListContract.View) {
        this.view = view
    }

    override fun onViewReady(accessToken: String, origin: String, destination: String, fromDate: String) {
        view.showLoading()
        flightsUseCase.execute(accessToken, origin, destination, fromDate, ::onSuccess, ::onError)
    }

    override fun onStop() = flightsUseCase.dispose()

    private fun onSuccess(scheduleResource: ScheduleResource) {
        val scheduleList = scheduleResource.schedule

        if (scheduleList.isNotEmpty()) {
            view.showFlights(scheduleList)
            view.hideLoading()
        }
    }

    private fun onError(throwable: Throwable) {
        view.hideLoading()
        view.showError(throwable)
    }

}