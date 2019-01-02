package com.example.rviciana.safeflight.view.oauth

import com.example.rviciana.safeflight.data.NetworkConfig
import com.example.rviciana.safeflight.domain.bo.AirportsResponse
import com.example.rviciana.safeflight.domain.bo.OAuthResponse
import com.example.rviciana.safeflight.domain.usecase.AirportsUseCase
import com.example.rviciana.safeflight.domain.usecase.OAuthUseCase
import javax.inject.Inject

class OAuthPresenter(private val oAuthUseCase: OAuthUseCase,
                     private val airportsUseCase: AirportsUseCase
) : OAuthContract.Presenter {

    internal lateinit var view: OAuthContract.View

    override fun setView(view: OAuthContract.View) {
        this.view = view
    }

    override fun authCredentials() {
        oAuthUseCase.execute(::onAuthSuccess, ::onError)
    }

    override fun onStop() {
        oAuthUseCase.dispose()
        airportsUseCase.dispose()
    }

    private fun onAuthSuccess(oAuthResponse: OAuthResponse) {
        view.saveOAuthToken(oAuthResponse)
        airportsUseCase.execute(oAuthResponse.accessToken,
                NetworkConfig.LIMIT,
                NetworkConfig.OFFSET,
                ::onFlightsSuccess,
                ::onError)
    }

    private fun onFlightsSuccess(airportsResponse: AirportsResponse) {
        view.navigateToFormActivity(airportsResponse)
    }

    private fun onError(throwable: Throwable) {
        view.showError(throwable)
    }

}