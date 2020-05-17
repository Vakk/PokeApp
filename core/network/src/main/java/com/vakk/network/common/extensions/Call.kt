package com.vakk.network.common.extensions

import okhttp3.Headers
import retrofit2.Call
import retrofit2.HttpException

fun <T : Any> Call<T>.toApiResult(withHeaders: Boolean = false): ApiResult<T> {
    val response = execute()
    val headers = if (withHeaders) {
        response.headers().toMap()
    } else {
        emptyMap()
    }
    return if (response.isSuccessful) {
        response.body()?.let {
            ApiResult(it)
        } ?: ApiResult(
            ApiError(
                errorCode = ErrorCode.UNKNOWN
            )
        )
    } else {
        val httpException = HttpException(response)
        return ApiResult(
            ApiError(
                errorCode = ErrorCode[httpException.code()]
                    ?: ErrorCode.UNKNOWN,
                errorThrowable = httpException
            )
        )
    }.apply {
        this.headers = headers
    }
}

private fun Headers.toMap(): Map<String, List<String>> {
    val result = mutableMapOf<String, List<String>>()
    for (name in names()) {
        result[name] = values(name)
    }
    return result
}