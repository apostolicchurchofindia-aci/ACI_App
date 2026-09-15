package com.aci.data.content

import android.content.Context
import com.aci.core.domain.repository.SongRepository
import timber.log.Timber

/**
 * Populates the Room-backed [SongRepository] on first launch.
 *
 * Source of truth, in order: the live ACI song spreadsheet ([RemoteSongSource]) first;
 * if that's unreachable or the sheet is empty/not shared publicly, falls back to the
 * bundled `assets/data/songs.json` + `song_lyrics.json` (requires [ChurchContent.init] to
 * have already run).
 */
object SongSeeder {
    suspend fun seedIfNeeded(repository: SongRepository, context: Context) {
        if (!repository.isEmpty()) return

        val remote = RemoteSongSource.tryFetch()
        if (remote != null) {
            Timber.i("SongSeeder: seeding ${remote.songs.size} songs from the live ACI spreadsheet")
            repository.upsertAll(songs = remote.songs, lyrics = remote.lyrics)
            return
        }

        Timber.i("SongSeeder: spreadsheet unavailable, seeding from bundled JSON instead")
        ChurchContent.init(context)
        repository.upsertAll(
            songs = ChurchContent.songs,
            lyrics = ChurchContent.songLyrics,
            media = ChurchContent.songMedia
        )
    }
}
