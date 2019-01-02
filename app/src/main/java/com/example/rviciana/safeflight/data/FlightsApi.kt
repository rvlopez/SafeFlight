package com.example.rviciana.safeflight.data

import com.example.rviciana.safeflight.data.dto.AirportsResponseDto
import com.example.rviciana.safeflight.data.dto.OAuthDto
import com.example.rviciana.safeflight.data.dto.ScheduleResourceResponseDto
import io.reactivex.Single
import retrofit2.http.*

interface FlightsApi {

    companion object {
        const val AUTHORIZATION = "Authorization"
        const val LIMIT = "limit"
        const val OFFSET = "offset"
        const val ORIGIN = "origin"
        const val DESTINATION = "destination"
        const val FROM_DATE_TIME = "fromDateTime"
    }

    @POST("oauth/token")
    @FormUrlEncoded
    fun authCredentials(@Field("client_id") clientId: String,
                        @Field("client_secret") clientSecret: String,
                        @Field("grant_type") grantType: String): Single<OAuthDto>

    @GET("references/airports/")
    fun getAllAirports(@Header(AUTHORIZATION) accessToken: String,
                       @Query(LIMIT) limit: Int,
                       @Query(OFFSET) offset: Int
    ): Single<AirportsResponseDto>

    @GET("operations/schedules/{origin}/{destination}/{fromDateTime}")
    fun getFlights(@Header(AUTHORIZATION) accessToken: String,
                   @Path(ORIGIN) origin: String,
                   @Path(DESTINATION) destination: String,
                   @Path(FROM_DATE_TIME) fromDateTime: String
    ) : Single<ScheduleResourceResponseDto>

}