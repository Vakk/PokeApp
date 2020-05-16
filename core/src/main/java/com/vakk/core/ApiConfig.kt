package com.vakk.core

interface ApiConfig {

    val urlModel: UrlModel

    interface UrlModel {
        val baseApi: String
    }

    companion object {
        lateinit var instance: ApiConfig
    }
}