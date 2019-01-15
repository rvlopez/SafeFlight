package com.example.rviciana.safeflight.data.flights

import android.content.SharedPreferences
import com.example.rviciana.safeflight.data.FlightsApi
import com.example.rviciana.safeflight.data.NetworkConfig
import com.example.rviciana.safeflight.domain.bo.ScheduleResource
import com.example.rviciana.safeflight.domain.repository.FlightsRepository
import com.example.rviciana.safeflight.extensions.getValue
import io.reactivex.Single

class FlightsRepositoryImpl(private val flightsApi: FlightsApi,
                            private val flightsMapper: FlightsMapper,
                            private val sharedPrefs: SharedPreferences
) : FlightsRepository {

    override fun getFlights(origin: String, destination: String, fromDateTime: String)
            : Single<ScheduleResource> {
        return flightsApi.getFlights(
                sharedPrefs.getValue(NetworkConfig.OATUH_TOKEN),
                origin,
                destination,
                fromDateTime).map { flightsMapper.map(it) }
    }
}