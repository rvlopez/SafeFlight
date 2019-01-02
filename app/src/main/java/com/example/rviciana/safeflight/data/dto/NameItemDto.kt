package com.example.rviciana.safeflight.data.dto

import com.google.gson.annotations.SerializedName

data class NameItemDto(
        @SerializedName("@LanguageCode")
        val languageCode: String,
        @SerializedName("$")
        val name: String
)