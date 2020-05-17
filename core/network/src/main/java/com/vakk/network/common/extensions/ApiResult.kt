package com.vakk.network.common.extensions

import com.vakk.network.common.ResponseHandler

/**
 * Gets result or throws error (in case of api error is not null).
 */
fun <T> ApiResult<T>.throwExceptionIfError(vararg handlers: ResponseHandler<T>): ApiResult<T> {
    val error = apiError
    return when {
        value != null -> this
        error != null -> throw apiError?.errorThrowable!!
        else -> throw RuntimeException("Invalid data received : ${this}")
    }.apply {
        for (handler in handlers) {
            this.value?.let {
                handler.handle(it)
            }
        }
    }
}

fun <T> ApiResult<T>.acceptHandlers(vararg handlers: ResponseHandler<T>): ApiResult<T> {
    for (handler in handlers) {
        this.value?.let {
            handler.handle(it)
        }
    }
    return this
}


fun <T : Any> ApiResult<T>.getResultOrThrowError(vararg handlers: ResponseHandler<in T>): T {
    val value = value
    val error = apiError
    return when {
        value != null -> value
        error != null -> throw apiError?.errorThrowable!!
        else -> throw RuntimeException("Invalid data received : ${this}")
    }.apply {
        for (handler in handlers) {
            handler.handle(this)
        }
    }
}

data class ApiResult<T>(var value: T? = null) {
    var headers: Map<String, List<String>> = emptyMap()

    var apiError: ApiError<T>? = null
        private set

    constructor(apiError: ApiError<T>) : this(null) {
        this.apiError = apiError
    }
}