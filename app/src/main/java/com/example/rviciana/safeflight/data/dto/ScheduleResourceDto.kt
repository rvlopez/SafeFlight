package com.example.rviciana.safeflight.data.dto

import com.google.gson.annotations.SerializedName

data class ScheduleResourceDto(
        @SerializedName("Schedule")
        val schedule: List<ScheduleDto>
)