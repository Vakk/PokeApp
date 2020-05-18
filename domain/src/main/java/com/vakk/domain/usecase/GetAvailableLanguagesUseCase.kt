package com.vakk.domain.usecase

import com.vakk.domain.entity.Language

interface GetAvailableLanguagesUseCase {
    suspend operator fun invoke(): List<Language>
}