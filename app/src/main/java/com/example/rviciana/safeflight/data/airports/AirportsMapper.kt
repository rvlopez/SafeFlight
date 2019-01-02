package com.example.rviciana.safeflight.data.airports

import com.example.rviciana.safeflight.data.dto.AirportDto
import com.example.rviciana.safeflight.data.dto.AirportsResponseDto
import com.example.rviciana.safeflight.data.dto.NameItemDto
import com.example.rviciana.safeflight.domain.bo.*

class AirportsMapper {

    fun map(airportsResponseDto: AirportsResponseDto): AirportsResponse {
        val airport = airportsResponseDto.airportResource.airports.airport
        val airportResponse: AirportsResponse

        airport.apply {
            airportResponse = AirportsResponse(
                    AirportResource(
                            Airports(
                                    toAirportList(airport)
                            )
                    )
            )
        }

        return airportResponse
    }

    private fun toAirportList(airportList: List<AirportDto>): List<Airport> {
        return airportList.map {
            Airport(
                    it.airportCode,
                    Position(
                            Coordinate(
                                    it.position.coordinate.latitude,
                                    it.position.coordinate.longitude
                            )
                    ),
                    it.cityCode,
                    it.countryCode,
                    Names(
                            toNamesList(it.names.nameList)
                    ),
                    it.timeZoneId
            )
        }
    }

    private fun toNamesList(nameList: List<NameItemDto>): List<NameItem> =
            nameList.map {
                NameItem(it.languageCode, it.name)
            }

}