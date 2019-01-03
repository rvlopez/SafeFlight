package com.example.rviciana.safeflight.data.oauth

import com.example.rviciana.safeflight.data.FlightsApi
import com.example.rviciana.safeflight.data.dto.OAuthData
import com.example.rviciana.safeflight.domain.bo.OAuthResponse
import io.reactivex.Single

class OAuthRepositoryImpl(private val flightsApi: FlightsApi,
                          private val OAuthMapper: OAuthMapper
) : OAuthRepository {

    override fun authCredentials(oAuthData: OAuthData): Single<OAuthResponse> {
        return flightsApi.authCredentials(
                oAuthData.clientId,
                oAuthData.clientSecret,
                oAuthData.grantType
        ).map { OAuthMapper.map(it) }
    }

}