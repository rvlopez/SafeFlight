package com.example.rviciana.safeflight.view.map

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rviciana.safeflight.R
import com.example.rviciana.safeflight.config.getDaggerRule
import com.example.rviciana.safeflight.di.ApplicationComponent
import com.example.rviciana.safeflight.domain.bo.Schedule
import com.example.rviciana.safeflight.domain.bo.ScheduleResource
import com.example.rviciana.safeflight.domain.bo.TotalJourney
import com.example.rviciana.safeflight.domain.repository.FlightsRepository
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.interaction.BaristaSleepInteractions.sleep
import com.schibsted.spain.barista.rule.BaristaRule
import io.reactivex.Single
import it.cosenonjaviste.daggermock.DaggerMockRule
import org.junit.Rule
import org.junit.Test

class FlightMapListActivityTest {

    @get:Rule
    val activityRule = BaristaRule.create(FlightsMapListActivity::class.java)

    @get:Rule
    val daggerRule: DaggerMockRule<ApplicationComponent> = getDaggerRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val flightsRepository: FlightsRepository = mock()

    @Test
    fun test() {
        given {
            flightsRepository.getFlights("", "", "", "")
        }.willReturn(
                Single.just(
                        ScheduleResource(
                                listOf(
                                      Schedule(
                                              TotalJourney(
                                                      ""
                                              )
                                      )
                                )
                        )
                )
        )

        activityRule.launchActivity()

        sleep(1000)

        assertRecyclerViewItemCount(R.id.bottomSheetRecyclerView, 50)
        assertNotDisplayed(R.id.flightsError)
        assertNotDisplayed(R.id.flightsLoading)

    }
}