package com.vakk.network.service

import com.vakk.network.bean.GetLanguageBean
import com.vakk.network.bean.GetLanguagesProtoBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface LanguagesApiService {
    @GET("v2/language/")
    fun getAvailableLanguages(): Call<GetLanguagesProtoBean>

    @GET
    fun getLanguage(@Url url: String): Call<GetLanguageBean>
}