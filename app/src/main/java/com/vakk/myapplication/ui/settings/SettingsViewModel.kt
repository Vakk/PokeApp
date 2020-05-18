package com.vakk.myapplication.ui.settings

import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bnology.ui.mvvm.ActionLiveData
import com.vakk.common.cast
import com.vakk.common.launchSafe
import com.vakk.domain.entity.Language
import com.vakk.domain.usecase.GetAvailableLanguagesUseCase
import com.vakk.domain.usecase.SaveLanguageUseCase
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    context: Context,
    val getAvailableLanguagesUseCase: GetAvailableLanguagesUseCase,
    val saveLanguageUseCase: SaveLanguageUseCase
) : AndroidViewModel(
    context.cast()
) {
    val onLanguagesLoaded = MutableLiveData<List<Language>>()

    val onLanguageChanged = ActionLiveData<Language>()

    init {
        loadLanguages()
    }

    fun loadLanguages() = viewModelScope.launchSafe {
        val languages = getAvailableLanguagesUseCase()
        onLanguagesLoaded.value = languages
    }

    fun changeLanguage(language: Language) = viewModelScope.launchSafe {
        saveLanguageUseCase(language)
        onLanguageChanged.value = language
    }
}