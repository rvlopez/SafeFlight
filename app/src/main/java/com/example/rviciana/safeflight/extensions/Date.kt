package com.example.rviciana.safeflight.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.today() : String {
    val pattern = "yyyy-MM-dd"
    val formatter = SimpleDateFormat(pattern, Locale.GERMANY)
    return formatter.format(this)
}