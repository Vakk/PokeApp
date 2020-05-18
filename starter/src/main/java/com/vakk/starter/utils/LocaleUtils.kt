package com.vakk.starter.utils

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import android.text.TextUtils
import java.util.*

class LocaleUtils {

    companion object {
        private val ARABIC_LANGUAGE = "ar"
        private val ARABIC_LANGUAGE_VARIANT = "DZ"

        fun wrap(context: Context, language: String): Context {
            if (language.isEmpty()) {
                return context
            }
            val locale = localeFromLanguage(language)
            val configuration = prepareConfigurationByLocale(context, locale)
            return ContextWrapper(context.createConfigurationContext(configuration))
        }


        fun localeFromLanguage(language: String) =
            if (TextUtils.equals(language, ARABIC_LANGUAGE)) {
                Locale(language, ARABIC_LANGUAGE_VARIANT)
            } else {
                Locale(language)
            }

        fun prepareConfigurationByLocale(context: Context, locale: Locale): Configuration {
            val config = Configuration(context.resources.configuration)
            Locale.setDefault(locale)
            setSystemLocale(config, locale)
            config.setLayoutDirection(locale)
            return config
        }

        fun setSystemLocale(config: Configuration, locale: Locale) {
            config.setLocale(locale)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                config.setLocales(LocaleList(locale))
            }
        }
    }
}
