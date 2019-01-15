package com.example.rviciana.safeflight.view.oauth

import com.example.rviciana.safeflight.domain.bo.AirportsResponse

interface OAuthContract {

    interface View {
        fun navigateToFormActivity(airportsResponse: AirportsResponse)
        fun showLoading()
        fun hideLoading()
        fun showError(throwable: Throwable)
        fun hideError()
    }

    interface Presenter {
        fun setView(view: OAuthContract.View)
        fun authCredentials()
        fun onRetryAuthCredentials()
        fun onStop()
    }
}