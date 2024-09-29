package com.denishrynkevich.photomaptestapp.data.models.auth

import com.squareup.moshi.Json

data class UserResponse(
    @Json(name = "usedId") val userId: Int? = -1,
    @Json(name = "login") val login: String? = null,
    @Json(name = "token") val token: String? = null,
)
