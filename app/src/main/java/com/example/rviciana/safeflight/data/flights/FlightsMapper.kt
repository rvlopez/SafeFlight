package com.example.rviciana.safeflight.data.flights

import com.example.rviciana.safeflight.data.dto.ScheduleDto
import com.example.rviciana.safeflight.data.dto.ScheduleResourceResponseDto
import com.example.rviciana.safeflight.domain.bo.*

class FlightsMapper {

    fun map(scheduleResourceResponseDto: ScheduleResourceResponseDto): ScheduleResource {
        val scheduleList = scheduleResourceResponseDto.scheduleResource.schedule
        return ScheduleResource(
                toScheduleList(scheduleList)
        )
    }

    private fun toScheduleList(schedule: List<ScheduleDto>): List<Schedule> = schedule.map {
        Schedule(
                TotalJourney(
                        it.totalJourney.duration
                )
        )
    }
}