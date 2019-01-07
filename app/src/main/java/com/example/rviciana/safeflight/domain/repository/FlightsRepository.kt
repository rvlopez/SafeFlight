package com.example.rviciana.safeflight.domain.repository

import com.example.rviciana.safeflight.domain.bo.ScheduleResource
import io.reactivex.Single

interface FlightsRepository {

    fun getFlights(accessToken: String, origin: String, destination: String, fromDateTime: String)
            : Single<ScheduleResource>
}