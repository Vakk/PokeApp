package com.vakk.domain.usecase

import com.vakk.domain.entity.Language

interface SaveLanguageUseCase {
    suspend operator fun invoke(language: Language)
}