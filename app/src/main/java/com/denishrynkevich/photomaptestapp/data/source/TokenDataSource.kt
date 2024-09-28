package com.denishrynkevich.photomaptestapp.data.source

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class TokenDataSource @Inject constructor(
    private val preferences: SharedPreferences
){
    fun setAccessToken(token: String) = preferences.edit{
        putString(ACCESS_TOKEN, token)
    }

    fun getAccessToken(): String = preferences.getString(ACCESS_TOKEN, EMPTY_STRING).orEmpty()

    fun deleteToken() {
        preferences.edit {
            remove(ACCESS_TOKEN)
        }
    }
    companion object {
        private const val EMPTY_STRING = ""
        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
    }
}

