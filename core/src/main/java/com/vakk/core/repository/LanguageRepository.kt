package com.vakk.core.repository

import com.vakk.language.LanguageDao
import com.vakk.language.LanguageDto
import com.vakk.network.bean.GetLanguageBean
import com.vakk.network.common.extensions.getResultOrThrowError
import com.vakk.network.common.extensions.toApiResult
import com.vakk.network.datasource.LanguagesDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LanguageRepository(
    val languagesDataSource: LanguagesDataSource,
    val languageDao: LanguageDao
) {

    suspend fun getFromCache(skip: Int, take: Int): List<LanguageDto> =
        withContext(Dispatchers.IO) {
            languageDao.getAll(
                skip = skip,
                take = take
            )
        }

    suspend fun fetchRemoteData(): List<GetLanguageBean> = withContext(Dispatchers.IO) {
        val languageProto = languagesDataSource.languagesApiService.getAvailableLanguages()
            .toApiResult()
            .getResultOrThrowError()
        languageProto.results.mapNotNull {
            it.url?.let {
                languagesDataSource.languagesApiService.getLanguage(it).toApiResult().getResultOrThrowError()
            }
        }.apply {
            languageDao.insertAll(
                map {
                    LanguageDto(
                        id = it.id,
                        iso3166 = it.iso3166,
                        iso639 = it.iso639,
                        name = it.name
                    )
                }
            )
        }
    }
}