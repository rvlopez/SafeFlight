package com.example.rviciana.safeflight.extensions

import android.content.SharedPreferences

fun SharedPreferences.saveValue(key: String, value: String) {
    val editor = this.edit()
    editor.putString(key, value)
    editor.apply()
}

fun SharedPreferences.getValue(key: String) : String? = this.getString(key, "")