package com.example.rviciana.safeflight.data.dto

import com.google.gson.annotations.SerializedName

data class AirportsDto(
        @SerializedName("Airport")
        val airport: List<AirportDto>
)