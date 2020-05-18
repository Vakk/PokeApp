package com.vakk.core.usecase

import android.content.Context
import com.vakk.common.cast
import com.vakk.core.LanguageOwner
import com.vakk.domain.entity.Language
import com.vakk.domain.usecase.SaveLanguageUseCase
import javax.inject.Inject

class SaveLanguageUseCaseImpl @Inject constructor(
    val context: Context
) : SaveLanguageUseCase {
    override suspend fun invoke(language: Language) {
        context.cast<LanguageOwner>().language = language
    }
}