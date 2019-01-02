package com.example.rviciana.safeflight.data.dto

import com.google.gson.annotations.SerializedName

data class AirportResourceDto(
        @SerializedName("Airports")
        val airports: AirportsDto
)