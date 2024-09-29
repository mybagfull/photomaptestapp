package com.denishrynkevich.photomaptestapp.data.mappers.user

import com.denishrynkevich.photomaptestapp.data.models.auth.AuthResponse
import com.denishrynkevich.photomaptestapp.domain.model.AuthData
import com.denishrynkevich.photomaptestapp.domain.model.UserTokenData
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import javax.inject.Inject

class AuthDataResponseMapper @Inject constructor() {
    private val codeError = 400

    operator fun invoke(authResponse: Response<AuthResponse>): Response<AuthData> =
        with(authResponse) {
            if (isSuccessful && body() != null) {
                val authResponseBody = body()!!
                val userResponse = authResponseBody.data ?: return Response.error(
                    codeError, createErrorBody("""{"error": "No user data"}""")
                )

                val userId = userResponse.userId ?: return Response.error(
                    codeError,
                    createErrorBody("""{"error": "UserId is null"}""")
                )
                val login = userResponse.login ?: return Response.error(
                    codeError,
                    createErrorBody("""{"error": "Login is null"}""")
                )
                val token = userResponse.token ?: return Response.error(
                    codeError,
                    createErrorBody("""{"error": "Token is null"}""")
                )

                val userTokenData = UserTokenData(userId, login, token)

                val status = authResponseBody.status ?: return Response.error(
                    codeError,
                    createErrorBody("""{"error": "Status is null"}""")
                )

                val authData = AuthData(status, userTokenData)
                Response.success(authData)
            } else {
                Response.error(createErrorBody("Unknown error"), raw())
            }
        }


    private fun createErrorBody(errorStr: String): ResponseBody {
        return errorStr.toResponseBody("application/json".toMediaTypeOrNull())
    }
}
