package com.example.studentcontactapp

import android.content.Context

class SettingsManager(context: Context) {

    private val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun setDarkMode(value: Boolean) {
        prefs.edit().putBoolean("dark_mode", value).apply()
    }

    fun isDarkMode(): Boolean {
        return prefs.getBoolean("dark_mode", false)
    }

    fun setNotificationEnabled(value: Boolean) {
        prefs.edit().putBoolean("notif", value).apply()
    }

    fun isNotificationEnabled(): Boolean {
        return prefs.getBoolean("notif", true)
    }

    fun setFontSize(size: Int) {
        prefs.edit().putInt("font_size", size).apply()
    }

    fun getFontSize(): Int {
        return prefs.getInt("font_size", 14)
    }
}