package com.example.rviciana.safeflight.view.map

import com.example.rviciana.safeflight.domain.bo.Schedule
import com.example.rviciana.safeflight.domain.bo.ScheduleResource
import com.example.rviciana.safeflight.domain.bo.TotalJourney
import com.example.rviciana.safeflight.domain.usecase.FlightsUseCase
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class FlightsListPresenterTest {

    private var mockView: FlightsListContract.View = mock()
    private var mockFlightsUseCase: FlightsUseCase = mock()

    private val presenter by lazy { FlightsListPresenter(mockFlightsUseCase) }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter.setView(mockView)
    }

    @Test
    fun `should loading when fetch flights`() {
        presenter.onViewReady("", "", "", "")

        verify(mockView).showLoading()
    }

    @Test
    fun `should request data on view ready`() {
        presenter.onViewReady("", "", "", "")

        verify(mockFlightsUseCase).execute(any(), any(), any(), any(), any(), any())
    }

    @Test
    fun `should dispose usecase onstop`() {
        presenter.onStop()

        verify(mockFlightsUseCase).dispose()
    }

    @Test
    fun `should error view given error response`() {
        presenter.onError(Throwable())

        verify(mockView).showError(any())
    }

    @Test
    fun `should show flights when response has data`() {
        val scheduleResource = givenSuccessResponse()
        presenter.onSuccess(scheduleResource)

        verify(mockView).showFlights(scheduleResource.schedule)
        verify(mockView).hideLoading()
    }

    private fun givenSuccessResponse(): ScheduleResource =
            ScheduleResource(
                    listOf(
                            Schedule(
                                    TotalJourney(
                                            ""
                                    )
                            )
                    )
            )

}