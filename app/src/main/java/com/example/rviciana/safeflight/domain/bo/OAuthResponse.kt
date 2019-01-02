package com.example.rviciana.safeflight.domain.bo

data class OAuthResponse (
        var accessToken: String,
        var tokenType: String,
        var expiresIn: Long
)