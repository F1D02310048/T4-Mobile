package com.example.studentcontactapp.utils

import android.content.Context
import android.content.SharedPreferences

class PrefManager(ctx: Context) {

    private val prefs: SharedPreferences =
        ctx.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_FILE = "UserSession"
        private const val LOGIN_STATUS = "login_status"
        private const val USER_NAME = "user_name"
        private const val REMEMBER_STATUS = "remember_status"
    }

    fun setLogin(username: String, remember: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean(LOGIN_STATUS, true)
        editor.putString(USER_NAME, username)
        editor.putBoolean(REMEMBER_STATUS, remember)
        editor.apply()
    }

    fun checkLogin(): Boolean {
        return prefs.getBoolean(LOGIN_STATUS, false)
    }

    fun checkRemember(): Boolean {
        return prefs.getBoolean(REMEMBER_STATUS, false)
    }

    fun fetchUsername(): String {
        return prefs.getString(USER_NAME, "") ?: ""
    }

    fun clearSession() {
        prefs.edit().clear().apply()
    }
}