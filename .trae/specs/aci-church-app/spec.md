# ACI Church Android App — Product Requirements Document

## Overview
- **Summary**: A production-ready, enterprise-grade digital church ecosystem Android application for The Apostolic Church of India (ACI), built with Kotlin + Jetpack Compose + Material 3, supporting multilingual (Tamil, English, Telugu) engagement across Bible, Worship, Community, and Administration domains.
- **Purpose**: To provide ACI members, pastors, worship teams, Sunday School teachers, and administrators with a single unified digital platform for daily Bible engagement, weekly worship preparation, church community connection, and church operations management.
- **Target Users**:
  - Guest visitors exploring ACI
  - Church Members (Tamil, English, Telugu speaking)
  - Sunday School Students & Teachers
  - Worship Team Members & Worship Leaders
  - Pastors & Branch Admins
  - ACI Administrators & Super Administrators

## Goals
- Deliver a premium, cohesive digital worship experience centered on: Daily Bible → Bible Reader → Prayer, Weekly Song → Practice → Sunday Worship, and Post-Service → Sermon → Community flows.
- Support 10 role-based access levels with role-appropriate feature visibility.
- Achieve production-quality UI/UX across 75+ screens with realistic content, complete state handling (loading/empty/error/offline/success), and accessibility.
- Provide full multilingual support (Tamil, English, Telugu) with text-safe layouts.
- Establish offline-first Bible access, secure JWT auth, RBAC, audit logging, and encrypted media access.
- Implement ACI Design System (deep navy/indigo, warm gold, Material 3, 8px grid, 12–16px radius, light/dark mode).
- Ship with realistic sample data: books of the Bible (Tamil/English/Telugu), 50+ worship songs (lyrics + chords), sample sermons, events, branches, prayer requests, Sunday School lessons.

## Non-Goals
- iOS or web frontend implementation (scope is Android only; backend API contracts are designed to be cross-platform ready).
- Production payment gateway integration (simulated giving flow with no real card processing).
- Real YouTube video upload/transcoding (only URL/ID management + embed linking).
- Bible translation IP creation (only licensed/public-domain text; sample data uses KJV-equivalent sample verses for demonstration).
- Server/infra deployment (backend design documented; local Android app with sample data + mocked API layer implemented).

## Background & Context
- ACI is a multi-branch Indian church with congregations using Tamil, English, and Telugu.
- Core product pillars: Daily Bible Engagement, Weekly Sunday Worship Preparation, Church Community & Prayer, Sunday School Discipleship, Administration & Analytics.
- User profile indicates preference for strict TypeScript-style SOLID code; Android equivalent: Kotlin, Clean Architecture, MVVM, modular package structure, SOLID, no business logic in UI layer, proper logging (Timber), enterprise-grade state management (StateFlow).

## Functional Requirements

### FR-1: Role-Based Access Control (RBAC)
- Ten roles (Guest, Member, Sunday School Student, Sunday School Teacher, Worship Team Member, Worship Leader, Pastor, Branch Admin, ACI Admin, Super Admin) with distinct permission sets.
- Role-based navigation visibility and route gating.

### FR-2: Authentication & Profile
- Splash → Onboarding → Login (email/phone + OTP) → Branch Selection flow.
- User profile management (avatar, name, language, preferred branch, notification preferences).

### FR-3: Home Dashboard
- Good morning greeting, profile avatar, branch selector, notification bell.
- Daily Bible Verse card (3 languages, translation selector; actions: Read / Save / Share / Poster / Watch Video).
- Daily Verse Poster card (View / Download / Share / WhatsApp / Instagram).
- Daily Verse Video card (YouTube thumbnail + play action).
- This Sunday's Song card (key/BPM/worship leader; actions: Listen / Lyrics / Chords / Practice / YouTube / Favorite / PRACTICE SONG CTA).
- Upcoming Service card (date/time/branch/location; actions: Add reminder / Directions).
- Latest Sermon card (thumbnail/speaker/topic/duration; actions: Watch / Listen / Read notes).
- Prayer card ("How can we pray for you?" + SUBMIT PRAYER CTA).
- Upcoming Events list.

### FR-4: Daily Bible Verse System
- Admin-schedulable daily verse with Tamil/English/Telugu exact text.
- Per-day poster (professional Christian design, ACI-branded) and YouTube video link.
- Verse never paraphrased when displayed as scripture.

### FR-5: Daily Verse Poster System
- Admin can select verse, language, background, template, ACI branding; schedule.
- AI/design rule: verse placed in clear area without covering key background elements.
- Download / Share / WhatsApp / Social actions.

### FR-6: Daily Verse Video
- YouTube URL/ID/title/thumbnail/description/language/reference/date storage.
- Watch / Share / Open YouTube / Save actions.

### FR-7: Bible Reader
- Tamil / English / Telugu.
- Book → Chapter → Verse navigation, search, bookmark, highlight, notes, share, font size, reading progress.
- Offline access for sample books.

### FR-8: ACI Bible AI Assistant
- Ask questions, explain verse, related verses, character studies, topic studies, prayer generator, Bible study generator, Sunday School & sermon prep helpers.
- AI-explanation text visually distinct from actual scripture text.

### FR-9: Song Library
- Search by title/lyrics/theme/language/category/key/BPM/composer/artist.
- Tamil/English/Telugu.
- Song cards (artwork, title, language, key, BPM, favorite, play).

### FR-10: Song Details
- Lyrics, Chords, Audio, Video, PDF, Presentation slides, Practice track.
- Transpose, Capo, BPM, font size, auto-scroll, Stage mode, offline access, share, favorite.

### FR-11: Worship Stage Mode
- Large lyrics + chords, dark mode, auto-scroll, page nav, transpose, BPM, metronome, song sections, next-song preview.
- Optimized for distance readability.

### FR-12: Weekly Sunday Song
- Per-branch weekly song: date, song, key, BPM, worship leader, practice audio, YouTube, notes.
- Workflow: Draft → Review → Approved → Scheduled → Published → Archived.

### FR-13: Worship Setlist Builder
- Add songs, reorder, assign vocalist/musician, set key/BPM, add notes/transitions, share with team, export PDF, presentation mode.

### FR-14: Worship Team
- Team members, roles, Sunday schedule, assignments, rehearsal schedule, attendance, announcements.

### FR-15: AI Worship Assistant
- AI Song Recommendation (theme + verse + language + duration + count → setlist).
- AI Setlist Generator (theme/verse/duration → worship sequence).

### FR-16: Sermons
- Library with video/audio/notes/transcript/speaker/topic/references/series.
- Search, filter, favorite, share, download.

### FR-17: Live Service
- LIVE NOW indicator, live video/audio, current song, lyrics, Bible references, prayer, giving, share.

### FR-18: Events
- Categories: Sunday Service, Prayer Meeting, Bible Study, Youth, Women, Men, Children, Conference, Special.
- Details: image, title, date/time, location, branch, description, registration, QR check-in.

### FR-19: Prayer Wall
- Tabs: Prayer Requests / Answered Prayers / Testimonies.
- Submit (private/anonymous/church-wide), pray for, mark answered.

### FR-20: Testimonies
- Submit with title/description/media (if allowed), public/private toggle, admin approval gate.

### FR-21: Sunday School
- Student dashboard: lesson, Bible portion, memory verse, quiz, attendance, progress, exam, leaderboard.
- Teacher dashboard: students, attendance, lessons, memory verses, quiz results, progress.

### FR-22: Bible Verse Memorization
- Daily verse, goals, progress, streaks, leaderboard, branch ranking, share achievement.

### FR-23: Community
- Groups, ministry groups, announcements, member directory, testimonies, group discussions (privacy controls).

### FR-24: Branch Directory
- List + map: name, address, distance, pastor, service timings, contact, directions.
- Preferred branch selection.

### FR-25: Online Giving
- Categories: Tithe, Offering, Missions, Church Projects.
- Amount, payment method, recurring, history, receipts (simulated, no real card data stored).

### FR-26: Notifications
- Categories: Daily Verse, Poster, Video, Sunday Song, Service Reminder, Sermon, Prayer, Events, Sunday School, Announcements.
- Granular preferences.

### FR-27: Automated Content Cycle
- Daily cycle: Daily Verse → Poster → Video → Share → Study/Prayer.
- Weekly cycle: Sunday Song → Listen → Lyrics → Practice → Rehearsal → Worship.
- Post-service: Sermon → Study → Prayer → Testimony → Community.

### FR-28: My ACI
- Profile, branch, saved verses/songs, sermons, prayer requests, events, giving, Sunday School, memorization progress, notifications, settings (language/notifications/dark/privacy/account).

### FR-29: Admin Dashboard
- Statistics cards (members/branches/songs/sermons/events/prayer/active users/Sunday School).
- Modules: Members, Branches, Songs, Weekly Song, Daily Verse, Poster Generator, YouTube Manager, Sermons, Events, Notifications, Sunday School, Content Approval.

### FR-30: Content Calendar
- Day/Week/Month views.
- Types: Daily Verse, Poster, Video, Weekly Song, Sermon, Event, Announcement.
- Statuses: Draft / Review / Approved / Scheduled / Published / Archived.

### FR-31: Analytics
- Bible: views/searches/bookmarks/shares.
- Poster: views/downloads/shares.
- Video: plays/watchtime/YouTube clicks.
- Song: views/lyrics/audio/practice/favorites/downloads.
- Sermon: views/watchtime/audio/shares.
- Event: registrations/check-ins.
- Community: active members/group activity.

### FR-32: Multilingual Support
- Tamil / English / Telugu across all major screens.
- Layouts never assume English text length; Tamil/Telugu text doesn't break UI.
- Bible verses preserve exact approved translation.

### FR-33: State Handling
- Loading, empty, error, offline, success states; confirmation dialogs; permission states.

### FR-34: Design System
- Deep navy (#0B1E3F) / indigo accents, warm gold (#C9A227), white/neutral surfaces, 8px spacing, 12–16px rounded corners, Material 3, custom ACI theme, light/dark modes, reusable components.

### FR-35: Clickable Prototype Flows (Compose Navigation)
- New Member: Splash → Onboarding → Login → OTP → Branch Selection → Home
- Daily Verse: Home → Daily Verse → Poster → Share; Home → Daily Verse Video → YouTube
- Worship: Home → Sunday Song → Lyrics → Chords → Practice → Setlist
- Bible: Home → Bible → Book → Chapter → Verse → Notes/Bookmark
- Prayer: Home → Prayer → Submit Request → Confirmation
- Sunday School: Home → Sunday School → Lesson → Memory Verse → Quiz → Leaderboard
- Events: Home → Events → Event Details → Registration → QR Check-in

## Non-Functional Requirements

### NFR-1: Architecture
- Kotlin + Jetpack Compose + Material 3, MVVM + Clean Architecture (app/data/domain/ui layers), Navigation Compose, Kotlin Coroutines, StateFlow, Room for offline-first sample data, Repository pattern, UseCase layer, DI (Hilt-compatible modules declared; if Hilt not practical to fully wire, use manual ServiceLocator pattern that matches clean architecture boundaries).

### NFR-2: Quality
- SOLID principles, no business logic in composables, use ViewModel + StateFlow, unidirectional data flow, single-responsibility composables, proper error handling, no raw println/System.out (Timber or wrapper logger).

### NFR-3: Performance
- Lazy lists with keys, image caching (Coil), baseline profile-friendly code, no blocking work on main thread, paginated lists where applicable.

### NFR-4: Accessibility
- Semantics labels for all interactive elements, contrast ≥ WCAG AA, text scale-friendly layouts, content descriptions for icons/images.

### NFR-5: Security
- Sample data only (no real secrets); mocked JWT auth flow; no hardcoded credentials in source; preferences encrypted where stated; secure-media URL pattern documented.

### NFR-6: Testability
- Domain layer pure Kotlin (unit-testable without Android), ViewModels testable via fake repositories, UI tests feasible via Compose Test.

### NFR-7: Offline-First
- Bible, Songs, Sunday School lessons, cached via Room with sample seed data; UI gracefully degrades without network.

### NFR-8: Realism & Production Look
- Realistic sample content (66 Bible book names × 3 languages, 50+ songs with Tamil/English lyrics + chords, 10+ branches, 20+ sermons, 15+ events, 30+ prayer requests/testimonies, 12+ Sunday School lessons).

## Constraints
- **Technical**: Target is Android (minSdk 24, targetSdk 34), Kotlin 1.9+, Gradle 8.x. AI features use mocked responses within the app (no real LLM API calls in this Android project). Backend described via interface contracts + mocked repository layer.
- **Business**: Bible text must be exact when marked as scripture; no paraphrasing in scripture-display contexts. ACI branding reserved for ACI organization.
- **Dependencies**: Jetpack Compose BOM, Material 3, Navigation Compose, Coil, Room, Accompanist (Pager/Insets/Permissions), Timber, kotlinx-datetime, kotlinx-serialization.

## Assumptions
- Sample/seed Bible content uses public-domain equivalent text for demonstration. Production deployment must use properly licensed translations.
- Giving flow is UI-only simulation for this build; integrate Stripe/Razorpay via backend in production.
- YouTube playback opens via Android intent to official YouTube app/web (no embedded player licensing assumptions; intent-based navigation).
- Admin dashboard is in-app (accessible by admin roles) rather than a separate web frontend.
- The implementation prioritizes core user-facing flows + admin screens with fully navigable Compose UI + realistic sample data; full network layer uses a mock/fake repository that implements the same interfaces a real Retrofit API would.

## Open Questions
- [ ] Does ACI have official brand assets (logo SVG, exact color codes) that should replace the navy/gold defaults in the Design System?
- [ ] Preferred Bible translation(s) for each language (e.g., Tamil: TBS/TNBS, Telugu: TBS/BSI, English: KJV/NKJV/ESV) to use for seed text licensing?
- [ ] Whether giving should simulate Razorpay (India-common) or a generic gateway UI?
- [ ] Admin role: should the "ACI Admin" and "Super Admin" scopes include only the in-app dashboard screens, or is a separate web admin expected later?

---

## Acceptance Criteria

### AC-1: Project builds successfully with Gradle
- **Type**: `rule`
- **Given**: A clean Android project in `/Users/sureshbabuisreal/Documents/PersonalGithub/ACI_APK`
- **When**: `./gradlew assembleDebug` is executed
- **Then**: Build succeeds with exit code 0
- **Pass Condition**: Gradle assembleDebug exits 0 with no unresolved symbols
- **Evidence**: Command output of assembleDebug

### AC-2: All 75+ screens are navigable via bottom nav + Navigation Compose
- **Type**: `rule`
- **Given**: App launched on emulator/device
- **When**: User navigates through the 6 prototype flows listed in FR-35 plus Admin screens
- **Then**: Every screen in §37 Figma Screen List renders without crash and title/header matches screen purpose
- **Pass Condition**: Automated navigation smoke test (manual or Compose UI test) visits all screens and they render
- **Evidence**: Navigation test run log or screenshot gallery

### AC-3: Home Dashboard displays all 8 cards with realistic sample data
- **Type**: `rule`
- **Given**: App opened to Home as Member role
- **When**: Home screen fully loaded
- **Then**: Cards (Daily Verse, Poster, Video, Sunday Song, Upcoming Service, Latest Sermon, Prayer, Events) all display with non-placeholder Tamil/English content, correct actions visible
- **Pass Condition**: Home shows all 8 sections; no "lorem ipsum" or empty placeholder fields
- **Evidence**: Screenshot of Home screen + content source listing (seed data)

### AC-4: Bible Reader functions across 3 languages with offline access
- **Type**: `rule`
- **Given**: No network (flight mode on)
- **When**: User selects Tamil language, opens Gospel of John, chapter 3, taps verse 16, adds bookmark + note
- **Then**: Verse displays exact scripture text (not paraphrased), bookmark persists, note persists, navigation language switches correctly for Tamil/English/Telugu
- **Pass Condition**: Room sample data loads offline; bookmarks/notes CRUD works; language switch updates text
- **Evidence**: Offline mode screenshots + local DB verification

### AC-5: Song Library + Details + Stage Mode
- **Type**: `rule`
- **Given**: Library of 50+ sample songs
- **When**: User searches "Amazing Grace", opens details, taps Lyrics → Chords → Stage Mode → Practice
- **Then**: Song card displays key/BPM/language; lyrics/chords render; transpose/capo/BPM controls work; Stage Mode enters with large text and dark background; practice track audio intent launches
- **Pass Condition**: All 6 song detail features functional; Stage Mode enters and displays sections
- **Evidence**: Screenshots of Song Details + Stage Mode + audio/control interaction log

### AC-6: Weekly Sunday Song workflow surfaces on Home
- **Type**: `rule`
- **Given**: Admin has published a Sunday song for current branch + week
- **When**: Member opens Home
- **Then**: "This Sunday's Song" card shows correct song, key, BPM, worship leader, PRACTICE CTA; tap navigates to Song Practice
- **Pass Condition**: Home card matches the published record with correct date window logic
- **Evidence**: Seed data record + Home screenshot + navigation to Practice screen

### AC-7: Setlist Builder + Worship Team screens
- **Type**: `rule`
- **Given**: Worship Leader role
- **When**: User creates setlist, adds 4 songs, reorders, assigns vocalist/keyboard, shares with team
- **Then**: Setlist persists with order + assignments; Worship Team screen shows roles + schedule; exported PDF intent (or share intent) fires
- **Pass Condition**: CRUD on setlist, reorder reflected, team role list rendered
- **Evidence**: Setlist screenshot + Worship Team screen + share event log

### AC-8: Prayer Wall + Submit Prayer flow
- **Type**: `rule`
- **Given**: Member role
- **When**: Home → SUBMIT PRAYER, enters text, selects anonymous + church-wide, submits
- **Then**: Confirmation screen shows; Prayer Wall tab lists request (with anonymous badge); "Prayed for" button increments counter
- **Pass Condition**: Submit → Confirmation → Prayer Wall listing → interaction counter
- **Evidence**: Flow screenshots + persistence in local Room

### AC-9: Sunday School Student + Teacher dashboards with Quiz + Leaderboard
- **Type**: `rule`
- **Given**: Sample class with 3 lessons, 2 memory verses, 1 quiz, 6 students
- **When**: Student role opens dashboard → Lesson → Memory Verse → Quiz → Leaderboard; then Teacher role opens dashboard
- **Then**: Student progress bar fills; quiz graded with correct answer highlighting; leaderboard ranked by streak/score; teacher sees attendance + quiz results
- **Pass Condition**: All 4 student screens functional + teacher dashboard shows student metrics
- **Evidence**: Student + Teacher dashboard screenshots, quiz result persistence

### AC-10: Branch Directory + Giving + Notifications Preferences
- **Type**: `rule`
- **Given**: Sample 10+ branches, giving categories, notification categories
- **When**: User opens Branch Directory, selects preferred, then Giving → Tithe → Confirmation, then Settings → Notification Preferences
- **Then**: Branch is saved to profile; giving confirmation shows receipt number; notification preferences per-category toggle persist
- **Pass Condition**: All 3 features end-to-end with persistence
- **Evidence**: Screenshots + preferences DB state

### AC-11: Admin Dashboard modules + Content Calendar + Analytics
- **Type**: `rule`
- **Given**: Super Admin role
- **When**: User opens Admin Dashboard, visits Member, Branch, Song, Weekly Song, Daily Verse, Poster Generator, YouTube, Sermon, Event, Notification, Sunday School, Content Approval, Content Calendar, Analytics modules
- **Then**: Each module screen renders with seeded realistic data, stats cards show non-zero numbers, Content Calendar Day/Week/Month tabs render entries, Analytics charts (or stat grids) display numbers
- **Pass Condition**: 17 admin modules all render with content + navigation
- **Evidence**: Admin module screenshot gallery

### AC-12: Multilingual (Tamil/English/Telugu) UI stability
- **Type**: `rubric`
- **Dimension**: Layout integrity across Tamil, English, Telugu locales
- **Scale**: 1-5
- **Anchors**: 1 = Tamil/Telugu text truncates/overlaps on ≥3 screens; 3 = minor overflow on 1-2 screens, readable; 5 = every screen renders Tamil & Telugu without truncation/overflow/pixel breakage, text length differences handled with wrap/scroll
- **Pass Threshold**: >= 4
- **Evidence**: Screenshots of Home, Bible Reader, Song Details, Settings in all 3 languages

### AC-13: Production UI/UX polish — state handling & design system
- **Type**: `rubric`
- **Dimension**: Visual polish, state completeness, design system fidelity
- **Scale**: 1-5
- **Anchors**: 1 = bare wireframes, missing states, inconsistent styling; 3 = functional UI with some missing empty/error states and occasional spacing inconsistency; 5 = every screen has loading/empty/error/offline/success states; navy/gold/neutral palette consistent; 8px grid + 12–16 radius used uniformly; typography scale applied; light/dark mode supported
- **Pass Threshold**: >= 4
- **Evidence**: Screenshots for each state category + light/dark mode pairings

### AC-14: Architecture — Clean/MVVM + SOLID + no logic in UI
- **Type**: `rubric`
- **Dimension**: Code architecture quality
- **Scale**: 1-5
- **Anchors**: 1 = everything in Activities/Composables, global mutable state; 3 = ViewModels present but some logic leaks into composables; packages half-modular; 5 = strict clean layers (data/domain/ui/app), UseCases + Repository interfaces + Room DAOs, StateFlow UDF, zero business logic in composables, well-named single-responsibility composables, Timber logging, package-by-feature structure
- **Pass Threshold**: >= 4
- **Evidence**: Code review of package structure + 3 representative ViewModel-Composable pairs

### AC-15: Sample data realism & richness
- **Type**: `rubric`
- **Dimension**: Realism and volume of seeded content
- **Scale**: 1-5
- **Anchors**: 1 = 5-10 generic placeholder items; 3 = some variety but thin (≤20 songs, few branches); 5 = 66 Bible book names × 3 languages, 50+ songs with actual lyrics/chords in multiple languages, 10+ branches with unique addresses/pastors/service times, 20+ sermons, 15+ events, 30+ prayer/testimony entries, 12+ Sunday School lessons, admin dashboards populated
- **Pass Threshold**: >= 4
- **Evidence**: Sample data module listing + row counts from Room seed
