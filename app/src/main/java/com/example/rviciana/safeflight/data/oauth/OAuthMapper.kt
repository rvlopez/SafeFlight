package com.example.rviciana.safeflight.data.oauth

import com.example.rviciana.safeflight.data.dto.OAuthDto
import com.example.rviciana.safeflight.domain.bo.OAuthResponse

class OAuthMapper {

    fun map(oAuthDto: OAuthDto): OAuthResponse {
        return OAuthResponse(
                oAuthDto.accessToken,
                oAuthDto.tokenType,
                oAuthDto.expiresIn
        )
    }
}