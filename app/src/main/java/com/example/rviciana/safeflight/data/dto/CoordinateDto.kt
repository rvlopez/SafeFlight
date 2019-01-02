package com.example.rviciana.safeflight.data.dto

import com.google.gson.annotations.SerializedName

data class CoordinateDto(
        @SerializedName("Latitude")
        val latitude: Double,
        @SerializedName("Longitude")
        val longitude: Double
)