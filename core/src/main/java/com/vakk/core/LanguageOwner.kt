package com.vakk.core

import com.vakk.domain.entity.Language

/**
 * Quick solution to store language for current session.
 */
interface LanguageOwner {
    var language: Language?
}