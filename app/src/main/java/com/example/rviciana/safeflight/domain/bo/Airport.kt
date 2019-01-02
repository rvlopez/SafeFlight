package com.example.rviciana.safeflight.domain.bo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Airport(
        val airportCode: String,
        val position: Position,
        val cityCode: String,
        val countryCode: String,
        val names: Names,
        val timeZoneId: String
): Parcelable