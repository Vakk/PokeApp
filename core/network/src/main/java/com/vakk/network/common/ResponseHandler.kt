package com.vakk.network.common

/**
 * This is the handler used for response result receiving.
 * You can define your own logic for handle different error codes, etc...
 */
interface ResponseHandler<in T> {
    fun <F : T> handle(response: F)
}