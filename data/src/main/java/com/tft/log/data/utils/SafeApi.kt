package com.tft.log.data.utils

import retrofit2.Response

suspend fun <T> safeApiCall(
    call: suspend () -> Response<T>
): ApiResult<T> {
    return try {
        val response = call()
        if (response.isSuccessful && response.body() != null) {
            ApiResult.Success(data = response.body()!!)
        } else {
            ApiResult.Error(message = "Error: ${response.code()} ${response.message()}")
        }
    } catch (e: Exception) {
        ApiResult.Error(message = "Exception: ${e.message}")
    }
}

sealed interface ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>
    data class Error(val message: String) : ApiResult<Nothing>
}