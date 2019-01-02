package com.example.rviciana.safeflight.view.oauth.di

import com.example.rviciana.safeflight.data.airports.AirportsRepository
import com.example.rviciana.safeflight.data.oauth.OAuthRepository
import com.example.rviciana.safeflight.domain.usecase.AirportsUseCase
import com.example.rviciana.safeflight.domain.usecase.OAuthUseCase
import com.example.rviciana.safeflight.view.oauth.OAuthContract
import com.example.rviciana.safeflight.view.oauth.OAuthPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named

@Module
class OAuthModule {

    @Provides
    fun provideOAuthUseCase(oAuthRepository: OAuthRepository,
                            @Named("subscribeOn") subscribeOn: Scheduler,
                            @Named("observeOn") observeOn: Scheduler
    ): OAuthUseCase = OAuthUseCase(oAuthRepository, subscribeOn, observeOn)

    @Provides
    fun provideFlightsUseCase(airportsRepository: AirportsRepository,
                              @Named("subscribeOn") subscribeOn: Scheduler,
                              @Named("observeOn") observeOn: Scheduler
    ): AirportsUseCase = AirportsUseCase(airportsRepository, subscribeOn, observeOn)

    @Provides
    fun provideOAuthPresenter(oAuthUseCase: OAuthUseCase,
                              airportsUseCase: AirportsUseCase
    ): OAuthContract.Presenter = OAuthPresenter(oAuthUseCase, airportsUseCase)
}