package com.denishrynkevich.photomaptestapp.domain.repositories

import com.denishrynkevich.photomaptestapp.domain.model.AuthData
import com.denishrynkevich.photomaptestapp.domain.model.UserData
import com.denishrynkevich.photomaptestapp.util.ApiResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun registrationUser(user: UserData): Flow<ApiResponse<AuthData>>

    fun loginUser(user: UserData): Flow<ApiResponse<AuthData>>
}