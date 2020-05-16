package com.vakk.core

interface AppConfig {

    val urlModel: UrlModel

    interface UrlModel {
        val baseApi: String
    }

    companion object {
        lateinit var instance: AppConfig
    }
}