package com.example.rviciana.safeflight.data.flights

import com.example.rviciana.safeflight.data.FlightsApi
import com.example.rviciana.safeflight.data.NetworkConfig
import com.example.rviciana.safeflight.domain.bo.ScheduleResource
import io.reactivex.Single

class FlightsRepositoryImpl(private val flightsApi: FlightsApi,
                            private val flightsMapper: FlightsMapper
) : FlightsRepository {

    override fun getFlights(accessToken: String, origin: String, destination: String, fromDateTime: String)
            : Single<ScheduleResource> {
        return flightsApi.getFlights(
                NetworkConfig.BEARER + accessToken,
                origin,
                destination,
                fromDateTime).map { flightsMapper.map(it) }
    }
}