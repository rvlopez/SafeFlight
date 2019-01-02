package com.example.rviciana.safeflight.data.airports

import com.example.rviciana.safeflight.domain.bo.AirportsResponse
import io.reactivex.Single

interface AirportsRepository {

    fun getAllAirports(accessToken: String, limit: Int, offset: Int): Single<AirportsResponse>
}