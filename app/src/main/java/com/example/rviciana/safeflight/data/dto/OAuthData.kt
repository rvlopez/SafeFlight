package com.example.rviciana.safeflight.data.dto

import com.google.gson.annotations.SerializedName

data class OAuthData(
        @SerializedName("client_id")
        val clientId: String,
        @SerializedName("client_secret")
        val clientSecret: String,
        @SerializedName("grant_type")
        val grantType: String
)