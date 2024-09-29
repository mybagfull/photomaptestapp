package com.denishrynkevich.photomaptestapp.data.network.service

import com.denishrynkevich.photomaptestapp.data.models.auth.AuthResponse
import com.denishrynkevich.photomaptestapp.data.models.auth.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("account/signup")
    suspend fun signUp(@Body userDto: UserDto): Response<AuthResponse>

    @POST("account/signin")
    suspend fun signIn(@Body userDto: UserDto): Response<AuthResponse>
}