package com.example.rviciana.safeflight.data.dto

import com.google.gson.annotations.SerializedName

data class NamesDto(
        @SerializedName("Name")
        val nameList: List<NameItemDto>
)