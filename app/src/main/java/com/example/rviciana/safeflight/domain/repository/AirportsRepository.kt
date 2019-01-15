package com.example.rviciana.safeflight.domain.repository

import com.example.rviciana.safeflight.domain.bo.AirportsResponse
import io.reactivex.Single

interface AirportsRepository {

    fun getAllAirports(limit: Int, offset: Int): Single<AirportsResponse>
}