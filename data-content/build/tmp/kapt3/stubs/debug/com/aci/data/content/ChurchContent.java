package com.aci.data.content;

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
 *  written by real members), so the Prayer Wall starts genuinely empty and fills from real use.
 *
 * Provenance (see each JSON file / README for detail):
 * - `branches.json` / leadership names / service timings: real, published by ACI.
 * - `daily_verses.json` verse refs + `verse_videos.json`: real, from the ACI YouTube channel.
 *  English text is KJV (public domain); Tamil/Telugu scripture text is intentionally blank.
 * - `bible_books.json` / `bible_verses.json`: real, complete public-domain King James Version
 *  (66 books, ~31,100 verses). English only — Tamil/Telugu fields are intentionally blank.
 * - `songs.json` / `song_lyrics.json`: primarily fetched at runtime from the ACI song
 *  spreadsheet (see [SongSeeder] / [RemoteSongSource]); this bundled JSON is the offline
 *  fallback and includes real ChristianLyricz.com-sourced Telugu songs plus sample hymns.
 * - `about.json`: real, published by ACI (mission statement, core beliefs, founder/co-leader,
 *  ministries list, founding year/city, social handles).
 * - `events.json`: real (ACI's published monthly fasting prayer) — fabricated sample events
 *  from an earlier pass were removed rather than presented as real.
 * - Sermons: real speaker names only; titles/topics are sample (no public sermon catalogue).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<J\u0014\u0010=\u001a\b\u0012\u0004\u0012\u0002030\b2\u0006\u0010>\u001a\u00020?R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R*\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\t0\b@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR*\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\r0\b@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR*\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00100\b@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR*\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00130\b@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\fR\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u00198F\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR*\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u001c0\b@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\fR*\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00190\b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00190\b@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\fR*\u0010\"\u001a\b\u0012\u0004\u0012\u00020!0\b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020!0\b@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\fR*\u0010%\u001a\b\u0012\u0004\u0012\u00020$0\b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020$0\b@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\fR*\u0010(\u001a\b\u0012\u0004\u0012\u00020\'0\b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\'0\b@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\fR\u0011\u0010*\u001a\u00020\u00138F\u00a2\u0006\u0006\u001a\u0004\b+\u0010,R*\u0010.\u001a\b\u0012\u0004\u0012\u00020-0\b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020-0\b@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010\fR*\u00101\u001a\b\u0012\u0004\u0012\u0002000\b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002000\b@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010\fR*\u00104\u001a\b\u0012\u0004\u0012\u0002030\b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002030\b@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010\fR*\u00107\u001a\b\u0012\u0004\u0012\u0002060\b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002060\b@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010\f\u00a8\u0006@"}, d2 = {"Lcom/aci/data/content/ChurchContent;", "", "()V", "<set-?>", "Lcom/aci/core/domain/model/AboutInfo;", "about", "getAbout", "()Lcom/aci/core/domain/model/AboutInfo;", "", "Lcom/aci/core/domain/model/BranchService;", "branchServices", "getBranchServices", "()Ljava/util/List;", "Lcom/aci/core/domain/model/BranchStaff;", "branchStaff", "getBranchStaff", "Lcom/aci/core/domain/model/Branch;", "branches", "getBranches", "Lcom/aci/core/domain/model/DailyVerse;", "dailyVerses", "getDailyVerses", "initialized", "", "latestSermon", "Lcom/aci/core/domain/model/Sermon;", "getLatestSermon", "()Lcom/aci/core/domain/model/Sermon;", "Lcom/aci/core/domain/model/SermonSeries;", "sermonSeries", "getSermonSeries", "sermons", "getSermons", "Lcom/aci/core/domain/model/SongLyricSection;", "songLyrics", "getSongLyrics", "Lcom/aci/core/domain/model/SongMedia;", "songMedia", "getSongMedia", "Lcom/aci/core/domain/model/Song;", "songs", "getSongs", "todayVerse", "getTodayVerse", "()Lcom/aci/core/domain/model/DailyVerse;", "Lcom/aci/core/domain/model/UserProfile;", "userProfiles", "getUserProfiles", "Lcom/aci/core/domain/model/User;", "users", "getUsers", "Lcom/aci/core/domain/model/VerseVideo;", "verseVideos", "getVerseVideos", "Lcom/aci/core/domain/model/WeeklySong;", "weeklySongs", "getWeeklySongs", "init", "", "context", "Landroid/content/Context;", "videosFor", "date", "Lkotlinx/datetime/LocalDate;", "data-content_debug"})
public final class ChurchContent {
    private static boolean initialized = false;
    private static java.util.List<com.aci.core.domain.model.Branch> branches;
    private static java.util.List<com.aci.core.domain.model.BranchService> branchServices;
    private static java.util.List<com.aci.core.domain.model.BranchStaff> branchStaff;
    private static java.util.List<com.aci.core.domain.model.User> users;
    private static java.util.List<com.aci.core.domain.model.UserProfile> userProfiles;
    private static java.util.List<com.aci.core.domain.model.DailyVerse> dailyVerses;
    private static java.util.List<com.aci.core.domain.model.VerseVideo> verseVideos;
    private static java.util.List<com.aci.core.domain.model.Song> songs;
    private static java.util.List<com.aci.core.domain.model.SongMedia> songMedia;
    private static java.util.List<com.aci.core.domain.model.SongLyricSection> songLyrics;
    private static java.util.List<com.aci.core.domain.model.WeeklySong> weeklySongs;
    private static java.util.List<com.aci.core.domain.model.SermonSeries> sermonSeries;
    private static java.util.List<com.aci.core.domain.model.Sermon> sermons;
    private static com.aci.core.domain.model.AboutInfo about;
    @org.jetbrains.annotations.NotNull()
    public static final com.aci.data.content.ChurchContent INSTANCE = null;
    
    private ChurchContent() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.aci.core.domain.model.Branch> getBranches() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.aci.core.domain.model.BranchService> getBranchServices() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.aci.core.domain.model.BranchStaff> getBranchStaff() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.aci.core.domain.model.User> getUsers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.aci.core.domain.model.UserProfile> getUserProfiles() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.aci.core.domain.model.DailyVerse> getDailyVerses() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.aci.core.domain.model.VerseVideo> getVerseVideos() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.aci.core.domain.model.Song> getSongs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.aci.core.domain.model.SongMedia> getSongMedia() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.aci.core.domain.model.SongLyricSection> getSongLyrics() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.aci.core.domain.model.WeeklySong> getWeeklySongs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.aci.core.domain.model.SermonSeries> getSermonSeries() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.aci.core.domain.model.Sermon> getSermons() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aci.core.domain.model.AboutInfo getAbout() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aci.core.domain.model.DailyVerse getTodayVerse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.aci.core.domain.model.Sermon getLatestSermon() {
        return null;
    }
    
    public final void init(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.aci.core.domain.model.VerseVideo> videosFor(@org.jetbrains.annotations.NotNull()
    kotlinx.datetime.LocalDate date) {
        return null;
    }
}