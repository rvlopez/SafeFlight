package com.example.rviciana.safeflight.view.oauth

import com.example.rviciana.safeflight.domain.bo.AirportsResponse
import com.example.rviciana.safeflight.domain.bo.OAuthResponse

interface OAuthContract {

    interface View {
        fun navigateToFormActivity(airportsResponse: AirportsResponse)
        fun showError(throwable: Throwable)
        fun saveOAuthToken(oAuthResponse: OAuthResponse)
    }

    interface Presenter {
        fun setView(view: OAuthContract.View)
        fun authCredentials()
        fun onStop()
    }
}