package com.example.rviciana.safeflight.domain.bo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AirportsResponse (
        val airportResource: AirportResource
) : Parcelable