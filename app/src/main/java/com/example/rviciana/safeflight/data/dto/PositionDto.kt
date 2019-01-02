package com.example.rviciana.safeflight.data.dto

import com.google.gson.annotations.SerializedName

data class PositionDto(
        @SerializedName("Coordinate")
        val coordinate: CoordinateDto
)