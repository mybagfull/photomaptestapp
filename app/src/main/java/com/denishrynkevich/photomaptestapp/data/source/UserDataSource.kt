package com.denishrynkevich.photomaptestapp.data.source

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val prefs: SharedPreferences
) {

    fun getToken(): String = prefs.getString(TOKEN_KEY, EMPTY_STRING).orEmpty()

    fun setToken(token: String) = prefs.edit {
        putString(TOKEN_KEY, token)
    }

    companion object {
        private const val EMPTY_STRING = ""
        private const val TOKEN_KEY = "TOKEN_KEY"
    }
}