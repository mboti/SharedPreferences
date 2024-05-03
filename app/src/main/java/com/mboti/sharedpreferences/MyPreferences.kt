package com.mboti.sharedpreferences

import android.content.Context

class MyPreferences (context: Context) {
    private val sharedPreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)

    fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String, defaultValue: String): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    // Similarly, you can implement methods for other data types
}