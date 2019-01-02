package com.example.rviciana.safeflight.domain.bo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NameItem(
        val languageCode: String,
        val name: String
): Parcelable