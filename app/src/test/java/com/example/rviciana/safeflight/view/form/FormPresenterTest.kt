package com.example.rviciana.safeflight.view.form

import com.example.rviciana.safeflight.domain.bo.*
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test

class FormPresenterTest {

    private val mockView: FormContract.View = mock()

    private val presenter by lazy { FormPresenter() }

    @Before
    fun setUp() {
        presenter.setView(mockView)
    }

    @Test
    fun `should navigate on airports success`() {
        val airport = givenAirport()
        presenter.onAirportsReady(airport, airport)

        verify(mockView).hideOriginInputError()
        verify(mockView).hideDestinationInputError()
        verify(mockView).navigateToFlightsListActivity(airport, airport)
    }

    @Test
    fun `should show origin input error`() {
        presenter.onOriginInputError()

        verify(mockView).showOriginInputError()
    }

    @Test
    fun `should show destination input error`() {
        presenter.onDestinationInputError()

        verify(mockView).showDestinationInputError()
    }

    @Test
    fun `should clear origin input`() {
        presenter.onOriginEraserClicked()

        verify(mockView).clearSearchViewOrigin()
    }

    @Test
    fun `should clear destination input`() {
        presenter.onDestinationEraserClicked()

        verify(mockView).clearSearchViewDestination()
    }

    private fun givenAirport(): Airport =
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
}