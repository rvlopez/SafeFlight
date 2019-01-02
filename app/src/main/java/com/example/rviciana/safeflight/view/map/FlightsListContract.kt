package com.example.rviciana.safeflight.view.map

import com.example.rviciana.safeflight.domain.bo.Schedule

interface FlightsListContract {

    interface View {
        fun showFlights(scheduleList: List<Schedule>)
        fun showLoading()
        fun hideLoading()
        fun showError(throwable: Throwable)
    }

    interface Presenter {
        fun setView(view: FlightsListContract.View)
        fun onViewReady(accessToken: String, origin: String, destination: String, fromDate: String)
        fun onStop()
    }
}