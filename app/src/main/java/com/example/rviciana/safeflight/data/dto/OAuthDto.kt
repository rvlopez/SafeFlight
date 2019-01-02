package com.example.rviciana.safeflight.data.dto

import com.google.gson.annotations.SerializedName

data class OAuthDto(
        @SerializedName("access_token")
        var accessToken: String,
        @SerializedName("token_type")
        var tokenType: String,
        @SerializedName("expires_in")
        var expiresIn: Long
)