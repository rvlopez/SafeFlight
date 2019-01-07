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

        verify(mockView).navigateToFlightsListActivity(airport, airport)
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