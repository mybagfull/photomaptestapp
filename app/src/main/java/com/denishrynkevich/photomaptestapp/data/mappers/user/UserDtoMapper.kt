package com.denishrynkevich.photomaptestapp.data.mappers.user

import com.denishrynkevich.photomaptestapp.data.models.auth.UserDto
import com.denishrynkevich.photomaptestapp.domain.model.UserData
import javax.inject.Inject

class UserDtoMapper @Inject constructor() {
    operator fun invoke(user: UserData): UserDto = with(user) {
        UserDto(
            login = login,
            password = password
        )
    }
}