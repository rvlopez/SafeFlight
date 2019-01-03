package com.example.rviciana.safeflight.data.airports

import com.example.rviciana.safeflight.data.FlightsApi
import com.example.rviciana.safeflight.data.NetworkConfig
import com.example.rviciana.safeflight.domain.bo.AirportsResponse
import io.reactivex.Single

class AirportsRepositoryImpl(private val flightsApi: FlightsApi,
                             private val airportsMapper: AirportsMapper
) : AirportsRepository {

    override fun getAllAirports(accessToken: String, limit: Int, offset: Int): Single<AirportsResponse> {
        return flightsApi.getAllAirports(
                NetworkConfig.BEARER + accessToken,
                limit,
                offset
        ).map { airportsMapper.map(it) }
    }
}