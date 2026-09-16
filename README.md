# ACI Church Android App

A digital church platform for The Apostolic Church of India (ACI) — Kotlin, Jetpack Compose, Material 3, Room, multi-module Gradle.

## Prerequisites

- **JDK 17+** on your `PATH` (`java -version`)
- **Android SDK** — set `sdk.dir` in `local.properties` at the repo root if it isn't already:
  ```properties
  sdk.dir=/Users/<you>/Library/Android/sdk
  ```
- An **emulator or physical device** running API 24+

## Run it

### Option A — Android Studio (easiest)

1. Open the project root in Android Studio.
2. Let Gradle sync finish.
3. Pick a device/emulator in the toolbar and click **Run ▶**.

### Option B — command line

1. Start an emulator (skip if a device is already connected):
   ```bash
   # list available emulators
   $ANDROID_HOME/emulator/emulator -list-avds

   # boot one
   $ANDROID_HOME/emulator/emulator -avd <avd_name> &
   ```
2. Build and install:
   ```bash
   ./gradlew :app:installDebug
   ```
3. Launch:
   ```bash
   adb shell am start -n com.aci.church.app/.MainActivity
   ```

### Just verify it builds

```bash
./gradlew assembleDebug
```

## What you'll see

The app opens on **Home**, with real content pulled from ACI's own site and YouTube channel (branch address/service time, today's Bible verse + verse video, real upcoming event, real Prayer/Bible CTAs). Five tabs, four of them real:

- **Bible** — the real, complete King James Version: 66 books, 31,100 verses, Room-backed. Browse by testament → book → chapter, read, bookmark, add notes, search across the whole text. English only (no licensed Tamil/Telugu source).
- **Songs** — Room-backed, 52 songs, 47 real (46 Telugu + 1 English) sourced verbatim from ChristianLyricz.com, tries a live Google Sheet first (see below).
- **Events** — Room-backed. Only the one real, ACI-published event (Monthly Fasting Prayer) — earlier fabricated sample events were removed rather than kept as filler. Full registration flow with a real confirmation ID.
- **Prayer** (via Home's Prayer card) — Room-backed Prayer Wall (Requests / Answered / Testimonies tabs), submit flow, pray-counter, mark-answered. Starts genuinely empty — no source exists for real prayer requests, so nothing is faked here; it fills from actual use.
- **My ACI** — shows the About ACI screen (real mission/beliefs/leadership) rather than a profile dashboard (no auth flow exists to have a "My ACI" profile yet).

See `tasks.md` for the full per-feature breakdown of what's real, what's sample, and what's not built.

## Data sources

**Everything is data-driven — no content is a Kotlin literal.** All seed content (branches, users, daily verses, songs, sermons, events, …) lives as JSON under [`data-content/src/main/assets/data/`](data-content/src/main/assets/data), loaded at runtime by [`ChurchContent.init(context)`](data-content/src/main/java/com/aci/data/content/ChurchContent.kt) (called once from `ACIChurchApplication.onCreate()`).

**Bible content** (`bible_books.json`, `bible_verses.json` — 66 books, 31,100 verses) is real: the public-domain King James Version, sourced from an open KJV dataset and cleaned with a small script (unwraps `{word}` italics markup in place, strips `{...: Heb. ...}` translator marginal notes entirely) — spot-checked against known text. It's seeded straight into Room by [`BibleSeeder`](data-content/src/main/java/com/aci/data/content/BibleSeeder.kt), bypassing `ChurchContent` entirely (31k objects aren't worth holding in memory permanently, only during the one-time seed).

**Prayer requests and testimonies are never seeded** — there's no real source for them (they're written by real members), and fabricating "Priya's father's surgery"-style sample entries was judged worse than an honest empty state. The Prayer Wall starts empty and fills only from real submissions.

**Songs specifically** try a live source first:

1. **Live spreadsheet** — [`RemoteSongSource`](data-content/src/main/java/com/aci/data/content/RemoteSongSource.kt) fetches the ACI song Google Sheet as CSV and parses it with [`SongCsvParser`](data-content/src/main/java/com/aci/data/content/SongCsvParser.kt).
2. **Bundled JSON fallback** — if the sheet is unreachable, not shared publicly, or empty, `SongSeeder` falls back to `assets/data/songs.json` + `song_lyrics.json` automatically. No crash either way.

**Note: I (Claude) have no Google Drive/Sheets write access** — no API or OAuth tool available to me can create or edit files there. The sheet at `1H1bxcekBmwI4tyj2TxgD-ieo7RqfhfCul_cvo9lzs_k` still returns empty to an unauthenticated request as of this writing, so the app is currently running entirely on the bundled JSON fallback (which is why it's kept well-stocked — 52 real songs and counting).

To point this at your own live sheet instead:

1. Open [`SONG_IMPORT_TEMPLATE.csv`](SONG_IMPORT_TEMPLATE.csv) — the exact column layout the parser expects, with two filled-in example rows and a blank one to copy.
2. Or paste in bulk: [`SONGS_BATCH_1_CHRISTIANLYRICZ.csv`](SONGS_BATCH_1_CHRISTIANLYRICZ.csv) (the same 40 songs already merged into the JSON fallback) is ready to paste wholesale into a Google Sheet in the same format — select all, paste starting at cell A1.
3. **Share → General access → Anyone with the link → Viewer** (the CSV export endpoint returns empty for anything narrower — this is the most common reason the live fetch silently falls back).
4. Confirm the `SHEET_ID` constant in `RemoteSongSource.kt` matches your sheet's ID (the long string in its URL between `/d/` and `/edit`).
5. Reinstall — next cold start (`adb shell pm clear com.aci.church.app`) will seed from your sheet instead of the JSON fallback.

The parser and template are covered by a real unit test: `./gradlew :data-content:test --tests "*SongCsvParserTest*"`.

## Project structure

```
app/                    — application shell, MainActivity, NavHost
core-domain/             — pure Kotlin models, enums, repository interfaces
core-data/               — Room database, DAOs, repository impls, ServiceLocator (manual DI)
core-ui/                 — ACI design system (theme, tokens, reusable components)
data-content/         — assets/data/*.json (all seed content) + JSON loader + CSV importer + Room seeder
feature-*/                — one module per app feature (home, songs, bible, worship, …)
SONG_IMPORT_TEMPLATE.csv — column layout for the live song spreadsheet
```

Each `feature-*` module is intentionally isolated (own `build.gradle.kts`, own package) so it can be built out independently — most are still empty scaffolds. See `tasks.md` for which ones have real screens.

## Troubleshooting

- **`Unable to find method makeConcatWithConstants` / kapt Java compile errors**: make sure every module's `compileOptions` sets both `sourceCompatibility` and `targetCompatibility` to `JavaVersion.VERSION_17` (already fixed project-wide, but worth checking if you add a new module).
- **`Cannot find a Java installation... languageVersion=17`**: don't add `kotlin { jvmToolchain(17) }` unless you actually have a JDK 17 installed and discoverable — prefer setting the Kotlin compile task's `jvmTarget` directly (see `core-domain/build.gradle.kts` for the pattern), which works against whatever JDK is already running Gradle.
- **Emulator black screen / splash stuck**: cold boot + Room DB seeding takes a few seconds on first launch after `adb shell pm clear com.aci.church.app` — wait it out before assuming a crash; check `adb logcat` for `FATAL EXCEPTION` to confirm either way. Also don't `pm clear` and `am start` back-to-back immediately after `installDebug` — give the package manager a second to finish registering the new install first, or the launch can silently fail to attach.
- **Songs always seed from the JSON fallback, never the live sheet**: check `adb logcat | grep RemoteSongSource` — `EAI_NODATA` / `No address associated with hostname` means the emulator itself has no DNS (common on freshly booted AVDs, unrelated to the app). A real device or an emulator with working internet will actually reach Google. Separately, an empty-but-reachable response means the sheet's sharing is too narrow — see **Data sources** above.
