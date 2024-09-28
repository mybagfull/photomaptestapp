package com.denishrynkevich.photomaptestapp.domain.model

data class UserTokenData(
    val userId: Int,
    val login: String,
    val token: String
)