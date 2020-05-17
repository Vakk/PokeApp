package com.vakk.myapplication

import com.vakk.core.ApiConfig

class ApiConfigImpl : ApiConfig {
    override val urlModel = UrlModelImpl()

    class UrlModelImpl : ApiConfig.UrlModel {
        override val baseApi: String = "https://pokeapi.co/api/"
    }
}