package com.example.rviciana.safeflight.view.oauth

import com.example.rviciana.safeflight.data.NetworkConfig
import com.example.rviciana.safeflight.domain.bo.AirportsResponse
import com.example.rviciana.safeflight.domain.bo.OAuthResponse
import com.example.rviciana.safeflight.domain.usecase.AirportsUseCase
import com.example.rviciana.safeflight.domain.usecase.OAuthUseCase

class OAuthPresenter(private val oAuthUseCase: OAuthUseCase,
                     private val airportsUseCase: AirportsUseCase
) : OAuthContract.Presenter {

    internal lateinit var view: OAuthContract.View

    private fun requestAuthCredentials() {
        oAuthUseCase.execute(::onAuthSuccess, ::onError)
    }

    override fun setView(view: OAuthContract.View) {
        this.view = view
    }

    override fun authCredentials() {
        view.showLoading()
        requestAuthCredentials()
    }

    override fun onRetryAuthCredentials() {
        view.hideError()
        view.showLoading()
        requestAuthCredentials()
    }

    override fun onStop() {
        oAuthUseCase.dispose()
        airportsUseCase.dispose()
    }

    internal fun onAuthSuccess(oAuthResponse: OAuthResponse) {
        airportsUseCase.execute(NetworkConfig.LIMIT,
                NetworkConfig.OFFSET,
                ::onFlightsSuccess,
                ::onError)
    }

    internal fun onFlightsSuccess(airportsResponse: AirportsResponse) {
        view.navigateToFormActivity(airportsResponse)
    }

    internal fun onError(throwable: Throwable) {
        view.showError(throwable)
    }

}