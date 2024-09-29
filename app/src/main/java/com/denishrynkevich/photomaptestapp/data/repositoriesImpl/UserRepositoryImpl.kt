package com.denishrynkevich.photomaptestapp.data.repositoriesImpl

import com.denishrynkevich.photomaptestapp.data.mappers.user.AuthDataResponseMapper
import com.denishrynkevich.photomaptestapp.data.mappers.user.UserDtoMapper
import com.denishrynkevich.photomaptestapp.data.network.service.AuthService
import com.denishrynkevich.photomaptestapp.domain.model.UserData
import com.denishrynkevich.photomaptestapp.domain.repositories.UserRepository
import com.denishrynkevich.photomaptestapp.utils.ApiRequestFlow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDtoMapper: UserDtoMapper,
    private val authDataMapper: AuthDataResponseMapper,
    private val userService: AuthService,
    private val apiRequestFlow: ApiRequestFlow
) : UserRepository {

    override fun registrationUser(user: UserData) = apiRequestFlow {
        authDataMapper(userService.signUp(userDtoMapper(user)))
    }

    override fun loginUser(user: UserData) = apiRequestFlow {
        authDataMapper(userService.signIn(userDtoMapper(user)))
    }
}