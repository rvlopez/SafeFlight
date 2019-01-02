package com.example.rviciana.safeflight.domain.bo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Names(
        val nameList: List<NameItem>
): Parcelable