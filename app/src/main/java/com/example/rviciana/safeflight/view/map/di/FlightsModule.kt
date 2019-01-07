package com.example.rviciana.safeflight.view.map.di

import com.example.rviciana.safeflight.domain.repository.FlightsRepository
import com.example.rviciana.safeflight.domain.usecase.FlightsUseCase
import com.example.rviciana.safeflight.view.map.FlightsListContract
import com.example.rviciana.safeflight.view.map.FlightsListPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named

@Module
class FlightsModule {

    @Provides
    fun provideFlightsUseCase(flightsRepository: FlightsRepository,
                              @Named("subscribeOn") subscribeOn: Scheduler,
                              @Named("observeOn") observeOn: Scheduler
    ) : FlightsUseCase = FlightsUseCase(flightsRepository, subscribeOn, observeOn)

    @Provides
    fun provideFlightsListPresenter(flightsUseCase: FlightsUseCase) : FlightsListContract.Presenter
            = FlightsListPresenter(flightsUseCase)
}