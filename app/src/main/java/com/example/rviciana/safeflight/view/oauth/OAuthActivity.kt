package com.example.rviciana.safeflight.view.oauth

import android.app.Activity
import android.os.Bundle
import com.example.rviciana.safeflight.R
import com.example.rviciana.safeflight.SafeFlightsApplication
import com.example.rviciana.safeflight.domain.bo.AirportsResponse
import com.example.rviciana.safeflight.domain.bo.OAuthResponse
import com.example.rviciana.safeflight.extensions.hide
import com.example.rviciana.safeflight.extensions.invisible
import com.example.rviciana.safeflight.extensions.saveValue
import com.example.rviciana.safeflight.extensions.show
import com.example.rviciana.safeflight.view.NavigatorImpl
import com.example.rviciana.safeflight.view.RootActivity
import com.example.rviciana.safeflight.view.oauth.di.OAuthModule
import kotlinx.android.synthetic.main.activity_oauth.*
import javax.inject.Inject

class OAuthActivity : RootActivity(), OAuthContract.View {

    @Inject lateinit var presenter: OAuthContract.Presenter

    private val Activity.app: SafeFlightsApplication get() = application as SafeFlightsApplication
    private val component by lazy { app.component.plus(OAuthModule()) }

    companion object {
        const val OATUH_TOKEN = "oauth_token"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authCredentials()
        retryConnection.setOnClickListener { presenter.onRetryAuthCredentials() }
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    private fun authCredentials() {
        presenter.authCredentials()
    }

    override fun initializeInjector() {
        component.inject(this@OAuthActivity)
    }

    override fun initializePresenter() {
        presenter.setView(this)
    }

    override fun getLayoutResourceId(): Int = R.layout.activity_oauth

    override fun navigateToFormActivity(airportsResponse: AirportsResponse) {
        NavigatorImpl(this@OAuthActivity).navigateToFormActivity(airportsResponse)
        finish()
    }

    override fun showLoading() = oauthLoading.show()

    override fun hideLoading() = oauthLoading.hide()

    override fun showError(throwable: Throwable) {
        oauthLoading.invisible()
        retryConnection.show()
        oauthError.show()
        oauthInfo.text = throwable.message
    }

    override fun hideError() {
        oauthError.hide()
        retryConnection.hide()
        oauthInfo.text = getString(R.string.oauth_info)
    }

    override fun saveOAuthToken(oAuthResponse: OAuthResponse) {
        val sharedPreferences = app.sharedPreferences
        sharedPreferences.saveValue(OATUH_TOKEN, oAuthResponse.accessToken)
    }
}