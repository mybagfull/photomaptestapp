package com.denishrynkevich.photomaptestapp.data.repositoriesImpl

import com.denishrynkevich.photomaptestapp.data.mappers.userMappers.AuthDataResponseMapper
import com.denishrynkevich.photomaptestapp.data.mappers.userMappers.UserDtoMapper
import com.denishrynkevich.photomaptestapp.data.network.AuthService
import com.denishrynkevich.photomaptestapp.domain.model.UserData
import com.denishrynkevich.photomaptestapp.domain.repositories.UserRepository
import com.denishrynkevich.photomaptestapp.util.ApiRequestFlow
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