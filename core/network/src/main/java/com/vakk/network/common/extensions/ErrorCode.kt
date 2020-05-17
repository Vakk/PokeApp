package com.vakk.network.common.extensions

private var errorCode: Int = 0

/**
 * You can define own error codes here.
 */
enum class ErrorCode(val code: Array<Int> = arrayOf(++errorCode)) {
    UNKNOWN,
    BAD_REQUEST(arrayOf(400, 412)),
    AUTHORIZATION(arrayOf(401));

    companion object {
        operator fun get(code: Int): ErrorCode? {
            return values().firstOrNull { it.code.contains(code) }
        }
    }
}