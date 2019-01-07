package com.example.rviciana.safeflight.view.oaut

import com.example.rviciana.safeflight.domain.bo.*
import com.example.rviciana.safeflight.domain.usecase.AirportsUseCase
import com.example.rviciana.safeflight.domain.usecase.OAuthUseCase
import com.example.rviciana.safeflight.view.oauth.OAuthContract
import com.example.rviciana.safeflight.view.oauth.OAuthPresenter
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class OAuthPresenterTest {

    private var mockView: OAuthContract.View = mock()
    private var mockOAuthUseCase: OAuthUseCase = mock()
    private var mockAirportsUseCase: AirportsUseCase = mock()

    private val presenter by lazy { OAuthPresenter(mockOAuthUseCase, mockAirportsUseCase) }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter.setView(mockView)
    }

    @Test
    fun `should loading when fetching oauth`() {
        presenter.authCredentials()

        verify(mockView).showLoading()
    }

    @Test
    fun `should auth request data on view ready`() {
        presenter.authCredentials()

        verify(mockOAuthUseCase).execute(any(), any())
    }

    @Test
    fun `should dispose usecase onstop`() {
        presenter.onStop()

        verify(mockOAuthUseCase).dispose()
        verify(mockAirportsUseCase).dispose()
    }

    @Test
    fun `should show error view given error response`() {
        presenter.onError(Throwable())

        verify(mockView).showError(any())
    }

    @Test
    fun `should save access token`() {
        val oAuthResponse = givenSuccessTokenResponse()
        presenter.onAuthSuccess(oAuthResponse)

        verify(mockView).saveOAuthToken(oAuthResponse)

    }

    @Test
    fun `should airports request data on auth success`() {
        val oAuthResponse = givenSuccessTokenResponse()
        presenter.onAuthSuccess(oAuthResponse)

        verify(mockAirportsUseCase).execute(any(), any(), any(), any(), any())
    }

    @Test
    fun `should navigate on airports data success`() {
        val airportsResponse = givenSuccessAirportsReponse()
        presenter.onFlightsSuccess(airportsResponse)

        verify(mockView).navigateToFormActivity(airportsResponse)
    }

    private fun givenSuccessTokenResponse(): OAuthResponse =
            OAuthResponse(
                    "", "", 0
            )

    private fun givenSuccessAirportsReponse(): AirportsResponse =
            AirportsResponse(
                    AirportResource(
                            Airports(
                                    listOf(
                                            Airport(
                                                    "",
                                                    Position(
                                                            Coordinate(
                                                                    0.0,
                                                                   0.0
                                                            )
                                                    ),
                                                    "",
                                                    "",
                                                    Names(
                                                         listOf(
                                                                 NameItem(
                                                                         "",
                                                                         ""
                                                                 )
                                                         )
                                                    ),
                                                    ""
                                            )
                                    )
                            )
                    )
            )
}