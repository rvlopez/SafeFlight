package com.example.rviciana.safeflight.view.oauth

import com.example.rviciana.safeflight.domain.bo.AirportsResponse
import com.example.rviciana.safeflight.domain.bo.OAuthResponse

interface OAuthContract {

    interface View {
        fun navigateToFormActivity(airportsResponse: AirportsResponse)
        fun showLoading()
        fun hideLoading()
        fun showError(throwable: Throwable)
        fun hideError()
        fun saveOAuthToken(oAuthResponse: OAuthResponse)
    }

    interface Presenter {
        fun setView(view: OAuthContract.View)
        fun authCredentials()
        fun onRetryAuthCredentials()
        fun onStop()
    }
}