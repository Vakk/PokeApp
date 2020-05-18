package com.vakk.core.usecase

import com.vakk.core.repository.LanguageRepository
import com.vakk.domain.entity.Language
import com.vakk.domain.usecase.GetAvailableLanguagesUseCase
import javax.inject.Inject

class GetAvaialbleLanguagesUseCaseImpl @Inject constructor(
    val languageRepository: LanguageRepository
) : GetAvailableLanguagesUseCase {
    override suspend fun invoke(): List<Language> {
        val cacheData = getFromCache()
        if (cacheData.isNotEmpty()) {
            return cacheData
        }
        languageRepository.fetchRemoteData()
        return getFromCache()
    }

    private suspend fun getFromCache(): List<Language> {
        val cacheData = languageRepository.getFromCache(0, 100)
        return if (cacheData.isNotEmpty()) {
            cacheData.map {
                Language(
                    id = it.id,
                    name = it.name,
                    iso639 = it.iso639,
                    iso3166 = it.iso3166
                )
            }
        } else {
            emptyList()
        }
    }
}