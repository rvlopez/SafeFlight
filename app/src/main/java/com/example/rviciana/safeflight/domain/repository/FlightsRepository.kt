package com.example.rviciana.safeflight.domain.repository

import com.example.rviciana.safeflight.domain.bo.ScheduleResource
import io.reactivex.Single

interface FlightsRepository {

    fun getFlights(origin: String, destination: String, fromDateTime: String)
            : Single<ScheduleResource>
}