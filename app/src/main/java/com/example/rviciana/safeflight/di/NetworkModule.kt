package com.example.rviciana.safeflight.di

import com.example.rviciana.safeflight.data.FlightsApi
import com.example.rviciana.safeflight.data.NetworkConfig
import com.example.rviciana.safeflight.data.airports.AirportsMapper
import com.example.rviciana.safeflight.data.airports.AirportsRepository
import com.example.rviciana.safeflight.data.airports.AirportsRepositoryImpl
import com.example.rviciana.safeflight.data.flights.FlightsMapper
import com.example.rviciana.safeflight.data.flights.FlightsRepository
import com.example.rviciana.safeflight.data.flights.FlightsRepositoryImpl
import com.example.rviciana.safeflight.data.oauth.OAuthMapper
import com.example.rviciana.safeflight.data.oauth.OAuthRepository
import com.example.rviciana.safeflight.data.oauth.OAuthRepositoryImpl
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
class NetworkModule {

    @Provides
    fun provideFlightsApi(retrofit: Retrofit): FlightsApi = retrofit.create()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder().apply {
        addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        addConverterFactory(GsonConverterFactory.create())
        baseUrl(NetworkConfig.API_URL)
        client(okHttpClient)
    }.build()

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient
            = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    fun provideOAuthMapper(): OAuthMapper = OAuthMapper()

    @Provides
    fun provideAirportsMapper(): AirportsMapper = AirportsMapper()

    @Provides
    fun provideFlightsMapper(): FlightsMapper = FlightsMapper()

    @Provides
    fun provideOAuthRepository(flightsApi: FlightsApi,
                               oAuthMapper: OAuthMapper
    ): OAuthRepository = OAuthRepositoryImpl(flightsApi, oAuthMapper)

    @Provides
    fun provideAirportsRepository(flightsApi: FlightsApi,
                                 airportsMapper: AirportsMapper
    ): AirportsRepository = AirportsRepositoryImpl(flightsApi, airportsMapper)

    @Provides
    fun provideFlightsRepository(flightsApi: FlightsApi,
                                 flightsMapper: FlightsMapper
    ) : FlightsRepository = FlightsRepositoryImpl(flightsApi, flightsMapper)
}