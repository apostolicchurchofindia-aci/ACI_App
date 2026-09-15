package com.aci.data.content

import com.aci.core.domain.enum.Language
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * App-wide selected content language (English/Tamil/Telugu), chosen from the Home screen
 * dropdown. In-memory only — not persisted across process death, same as [AdminSession]/auth
 * state in this local-only build. Screens that have real translated content (Bible, songs)
 * read this to decide which language field to render, falling back to English wherever a
 * translation is missing for a given item.
 */
object AppLanguageSession {
    private val _current = MutableStateFlow(Language.ENGLISH)
    val current: StateFlow<Language> = _current.asStateFlow()

    fun select(language: Language) {
        _current.value = language
    }
}
