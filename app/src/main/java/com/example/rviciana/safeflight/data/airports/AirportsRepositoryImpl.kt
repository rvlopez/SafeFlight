package com.example.rviciana.safeflight.data.airports

import android.content.SharedPreferences
import com.example.rviciana.safeflight.data.FlightsApi
import com.example.rviciana.safeflight.data.NetworkConfig
import com.example.rviciana.safeflight.domain.bo.AirportsResponse
import com.example.rviciana.safeflight.domain.repository.AirportsRepository
import com.example.rviciana.safeflight.extensions.getValue
import io.reactivex.Single

class AirportsRepositoryImpl(private val flightsApi: FlightsApi,
                             private val airportsMapper: AirportsMapper,
                             private val sharedPrefs: SharedPreferences
) : AirportsRepository {

    override fun getAllAirports(limit: Int, offset: Int): Single<AirportsResponse> {
        return flightsApi.getAllAirports(
                sharedPrefs.getValue(NetworkConfig.OATUH_TOKEN),
                limit,
                offset
        ).map { airportsMapper.map(it) }
    }
}