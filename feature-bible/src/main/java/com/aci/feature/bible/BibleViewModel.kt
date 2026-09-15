package com.aci.feature.bible

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aci.core.data.di.ServiceLocator
import com.aci.core.domain.model.BibleBook
import com.aci.data.content.AppLanguageSession
import com.aci.data.content.CurrentUser
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BibleViewModel : ViewModel() {
    private val repository = ServiceLocator.bibleRepository

    val selectedLanguage = AppLanguageSession.current

    val books: StateFlow<List<BibleBook>> = repository.observeBooks()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val bookmarkedVerseIds: StateFlow<Set<String>> = repository.observeBookmarks(CurrentUser.ID)
        .map { bookmarks -> bookmarks.map { it.verseId }.toSet() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptySet())

    /** No-op when signed out — the reading plan is a registered-member feature. */
    fun markChapterRead(bookId: String, chapter: Int) {
        viewModelScope.launch {
            val user = ServiceLocator.userRepository.observeCurrentUser().first() ?: return@launch
            ServiceLocator.readingPlanRepository.markChapterRead(user.id, bookId, chapter)
        }
    }

    fun toggleBookmark(verseId: String) {
        viewModelScope.launch { repository.toggleBookmark(CurrentUser.ID, verseId) }
    }

    fun addNote(verseId: String, text: String) {
        viewModelScope.launch {
            repository.addNote(
                com.aci.core.domain.model.VerseNote(
                    userId = CurrentUser.ID,
                    verseId = verseId,
                    text = text,
                    createdAt = kotlinx.datetime.Clock.System.now()
                )
            )
        }
    }
}
