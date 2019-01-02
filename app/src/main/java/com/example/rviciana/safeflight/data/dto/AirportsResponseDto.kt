package com.example.rviciana.safeflight.data.dto

import com.google.gson.annotations.SerializedName

data class AirportsResponseDto(
        @SerializedName("AirportResource")
        val airportResource: AirportResourceDto
)