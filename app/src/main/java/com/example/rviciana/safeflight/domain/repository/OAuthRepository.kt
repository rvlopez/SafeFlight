package com.example.rviciana.safeflight.domain.repository

import com.example.rviciana.safeflight.data.dto.OAuthData
import com.example.rviciana.safeflight.domain.bo.OAuthResponse
import io.reactivex.Single

interface OAuthRepository {

    fun authCredentials(oAuthData: OAuthData): Single<OAuthResponse>

}