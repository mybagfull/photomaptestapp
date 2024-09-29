package com.denishrynkevich.photomaptestapp.data.models.auth

import com.squareup.moshi.Json

data class UserDto(
    @Json(name = "login") val login: String,
    @Json(name = "password") val password: String,
)
