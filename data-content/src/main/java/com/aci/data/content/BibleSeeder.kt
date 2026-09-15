package com.aci.data.content

import android.content.Context
import com.aci.core.domain.model.BibleBook
import com.aci.core.domain.model.BibleVerse
import com.aci.core.domain.repository.BibleRepository
import timber.log.Timber

/**
 * Seeds the real, complete Bible into Room on first launch. Not routed through [ChurchContent]
 * — 31k verse objects aren't worth holding in memory permanently, only during this one-time seed.
 *
 * Text provenance:
 * - English: King James Version (public domain), 66 books, ~31,100 verses.
 * - Tamil & Telugu: Old Version (O.V.), © The Bible Society of India. Imported from the
 *   `tamil.db` / `telugu.db` modules supplied by the church under its licence from BSI
 *   (31,096 of 31,100 Tamil verses and 31,094 of 31,100 Telugu verses matched to the English
 *   versification; the small gap is normal cross-translation versification variance, not
 *   missing data, and those verses fall back to English).
 *
 *   Unlike the CC BY-SA text this replaced, the BSI translations are all-rights-reserved and
 *   are included here only under that licence — they may not be redistributed outside this
 *   app, and any further use needs BSI's agreement. TODO: replace the attribution rendered in
 *   the UI with the exact wording BSI's licence requires (their published form for Tamil runs
 *   along the lines of "BSI - Tamil - O.V. (New Ortho)"); confirm the required string for both
 *   languages with BSI and update [com.aci.core.domain.model.translationCodeFor].
 */
object BibleSeeder {
    suspend fun seedIfNeeded(repository: BibleRepository, context: Context) {
        if (!repository.isEmpty()) return

        val books: List<BibleBook> = JsonAssetLoader.load(context, "data/bible_books.json")
        val verses: List<BibleVerse> = JsonAssetLoader.load(context, "data/bible_verses.json")

        Timber.i("BibleSeeder: seeding ${books.size} books, ${verses.size} verses (KJV/en, BSI O.V./ta+te)")
        repository.upsertBooks(books)
        repository.upsertVerses(verses)
        Timber.i("BibleSeeder: done")
    }
}
