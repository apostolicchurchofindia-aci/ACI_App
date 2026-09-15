package com.aci.data.content

import android.content.Context
import com.aci.core.domain.model.AboutInfo
import com.aci.core.domain.model.Branch
import com.aci.core.domain.model.BranchService
import com.aci.core.domain.model.BranchStaff
import com.aci.core.domain.model.DailyVerse
import com.aci.core.domain.model.ReadingPlanDay
import com.aci.core.domain.model.Sermon
import com.aci.core.domain.model.SermonSeries
import com.aci.core.domain.model.Song
import com.aci.core.domain.model.SongLyricSection
import com.aci.core.domain.model.SongMedia
import com.aci.core.domain.model.User
import com.aci.core.domain.model.UserProfile
import com.aci.core.domain.model.VerseVideo
import com.aci.core.domain.model.WeeklySong
import kotlinx.datetime.Instant

/**
 * Single entry point for all bundled church content that's small enough to hold in memory.
 * Every field is loaded from a JSON file under `assets/data/` (see [JsonAssetLoader]) —
 * nothing here is a Kotlin data literal. Call [init] once (from Application.onCreate, before
 * anything reads these properties).
 *
 * Large or inherently user-generated content does NOT live here — it's seeded straight into
 * Room instead, without being held as a permanent in-memory list:
 * - Bible (31k+ verses): [BibleSeeder] → `BibleRepository`.
 * - Events: [EventSeeder] → `EventRepository`.
 * - Prayer requests / testimonies: nothing to seed — no real source exists for these (they're
 *   written by real members), so the Prayer Wall starts genuinely empty and fills from real use.
 *
 * Provenance (see each JSON file / README for detail):
 * - `branches.json` / leadership names / service timings: real, published by ACI.
 * - `daily_verses.json` verse refs + `verse_videos.json`: real, from the ACI YouTube channel.
 *   English text is KJV (public domain); Tamil/Telugu text is filled from the same BSI
 *   translations as the Bible below.
 * - `bible_books.json` / `bible_verses.json`: real, complete Bible (66 books, ~31,100 verses).
 *   English is the public-domain King James Version; Tamil/Telugu are the Bible Society of
 *   India Old Version, all rights reserved and used under the church's licence — see
 *   [BibleSeeder] for the full terms.
 * - `reading_plan.json`: generated, not sourced — a 365-day schedule of chapter references
 *   covering all 1,189 chapters in canonical order. Contains no scripture text.
 * - `songs.json` / `song_lyrics.json`: primarily fetched at runtime from the ACI song
 *   spreadsheet (see [SongSeeder] / [RemoteSongSource]); this bundled JSON is the offline
 *   fallback and includes real ChristianLyricz.com-sourced Telugu songs plus sample hymns.
 * - `about.json`: real, published by ACI (mission statement, core beliefs, founder/co-leader,
 *   ministries list, founding year/city, social handles).
 * - `events.json`: real (ACI's published monthly fasting prayer) — fabricated sample events
 *   from an earlier pass were removed rather than presented as real.
 * - Sermons: real speaker names only; titles/topics are sample (no public sermon catalogue).
 */
object ChurchContent {
    private var initialized = false

    lateinit var branches: List<Branch>; private set
    lateinit var branchServices: List<BranchService>; private set
    lateinit var branchStaff: List<BranchStaff>; private set

    lateinit var users: List<User>; private set
    lateinit var userProfiles: List<UserProfile>; private set

    lateinit var dailyVerses: List<DailyVerse>; private set
    lateinit var verseVideos: List<VerseVideo>; private set

    lateinit var songs: List<Song>; private set
    lateinit var songMedia: List<SongMedia>; private set
    lateinit var songLyrics: List<SongLyricSection>; private set

    lateinit var weeklySongs: List<WeeklySong>; private set

    lateinit var sermonSeries: List<SermonSeries>; private set
    lateinit var sermons: List<Sermon>; private set

    lateinit var about: AboutInfo; private set

    lateinit var readingPlan: List<ReadingPlanDay>; private set

    val todayVerse: DailyVerse get() = dailyVerses.first()
    val latestSermon: Sermon get() = sermons.maxByOrNull { it.preachedAt ?: Instant.DISTANT_PAST }!!

    fun init(context: Context) {
        if (initialized) return
        val loader = JsonAssetLoader

        branches = loader.load(context, "data/branches.json")
        branchServices = loader.load(context, "data/branch_services.json")
        branchStaff = loader.load(context, "data/branch_staff.json")

        users = loader.load(context, "data/users.json")
        userProfiles = loader.load(context, "data/user_profiles.json")

        dailyVerses = loader.load(context, "data/daily_verses.json")
        verseVideos = loader.load(context, "data/verse_videos.json")

        songs = loader.load(context, "data/songs.json")
        songMedia = loader.load(context, "data/song_media.json")
        songLyrics = loader.load(context, "data/song_lyrics.json")

        weeklySongs = loader.load(context, "data/weekly_songs.json")

        sermonSeries = loader.load(context, "data/sermon_series.json")
        sermons = loader.load(context, "data/sermons.json")

        about = loader.load(context, "data/about.json")

        readingPlan = loader.load(context, "data/reading_plan.json")

        initialized = true
    }

    fun videosFor(date: kotlinx.datetime.LocalDate) = verseVideos.filter { it.dailyVerseDate == date }
}
