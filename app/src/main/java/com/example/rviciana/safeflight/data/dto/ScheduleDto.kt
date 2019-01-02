package com.example.rviciana.safeflight.data.dto

import com.google.gson.annotations.SerializedName

data class ScheduleDto(
        @SerializedName("TotalJourney")
        val totalJourney: TotalJourneyDto
)