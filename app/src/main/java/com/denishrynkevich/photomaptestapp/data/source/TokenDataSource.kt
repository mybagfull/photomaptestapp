package com.denishrynkevich.photomaptestapp.data.source

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class TokenDataSource @Inject constructor(
    private val prefs: SharedPreferences
) {

    fun getAccessToken(): String = prefs.getString(ACCESS_TOKEN, EMPTY_STRING).orEmpty()


    fun setAccessToken(token: String) = prefs.edit {
        putString(ACCESS_TOKEN, token)
    }

    fun deleteToken() {
        prefs.edit {
            remove(ACCESS_TOKEN)
        }
    }

    companion object {
        private const val EMPTY_STRING = ""

        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
    }
}