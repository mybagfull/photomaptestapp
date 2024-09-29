package com.denishrynkevich.photomaptestapp.utils

import com.denishrynkevich.photomaptestapp.domain.model.ErrorData
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeoutOrNull
import retrofit2.Response
import javax.inject.Inject

class ApiRequestFlow @Inject constructor(
    private val errorAdapter: JsonAdapter<ErrorData>
) {

    operator fun <T> invoke(call: suspend () -> Response<T>): Flow<ApiResponse<T>> = flow {
        emit(ApiResponse.Loading)

        withTimeoutOrNull(20000L) {
            val response = call()

            try {
                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        emit(ApiResponse.Success(data))
                    }
                } else {
                    response.errorBody()?.let { error ->
                        error.close()
                        val parsedError: ErrorData? =
                            errorAdapter.fromJson(error.source())
                        emit(
                            ApiResponse.Failure(
                                parsedError?.message.toString(),
                                parsedError?.code ?: 0
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                emit(ApiResponse.Failure(e.message ?: e.toString(), 400))
            }
        } ?: emit(ApiResponse.Failure("Timeout! Please try again.", 408))
    }.flowOn(Dispatchers.IO)
}