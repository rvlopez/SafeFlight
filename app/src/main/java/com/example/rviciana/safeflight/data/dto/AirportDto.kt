package com.example.rviciana.safeflight.data.dto

import com.google.gson.annotations.SerializedName

data class AirportDto(
        @SerializedName("AirportCode")
        val airportCode: String,
        @SerializedName("Position")
        val position: PositionDto,
        @SerializedName("CityCode")
        val cityCode: String,
        @SerializedName("CountryCode")
        val countryCode: String,
        @SerializedName("Names")
        val names: NamesDto,
        @SerializedName("TimeZoneId")
        val timeZoneId: String
)