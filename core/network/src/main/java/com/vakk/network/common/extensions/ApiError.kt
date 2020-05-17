package com.vakk.network.common.extensions

/**
 * This class represents error data for response.
 */
class ApiError<T>(val errorCode: ErrorCode, val errorThrowable: Throwable? = null)