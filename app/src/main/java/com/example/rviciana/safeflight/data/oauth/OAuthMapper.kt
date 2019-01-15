package com.example.rviciana.safeflight.data.oauth

import android.content.SharedPreferences
import com.example.rviciana.safeflight.data.NetworkConfig
import com.example.rviciana.safeflight.data.dto.OAuthDto
import com.example.rviciana.safeflight.domain.bo.OAuthResponse
import com.example.rviciana.safeflight.extensions.saveValue

class OAuthMapper(private val sharedPrefs: SharedPreferences) {

    fun map(oAuthDto: OAuthDto): OAuthResponse {
        sharedPrefs.saveValue(NetworkConfig.OATUH_TOKEN, NetworkConfig.BEARER + oAuthDto.accessToken)
        return OAuthResponse(
                oAuthDto.accessToken,
                oAuthDto.tokenType,
                oAuthDto.expiresIn
        )
    }
}