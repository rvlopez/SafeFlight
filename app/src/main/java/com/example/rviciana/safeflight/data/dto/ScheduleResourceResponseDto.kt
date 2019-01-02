package com.example.rviciana.safeflight.data.dto

import com.google.gson.annotations.SerializedName

data class ScheduleResourceResponseDto(
        @SerializedName("ScheduleResource")
        val scheduleResource: ScheduleResourceDto
)