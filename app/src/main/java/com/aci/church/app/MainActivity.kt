package com.aci.church.app

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aci.core.data.di.ServiceLocator
import com.aci.core.ui.components.ACIBottomNav
import com.aci.core.ui.components.ACILoading
import com.aci.core.ui.components.ACITopBar
import com.aci.core.ui.components.defaultBottomNavItems
import com.aci.core.ui.components.defaultBottomNavItemsTamil
import com.aci.core.ui.components.defaultBottomNavItemsTelugu
import com.aci.core.ui.theme.ACITheme
import com.aci.data.content.AppLanguageSession
import com.aci.data.content.BranchIds
import com.aci.data.content.ChurchContent
import com.aci.feature.bible.AddNoteDialog
import com.aci.feature.bible.BibleBookmarksScreen
import com.aci.feature.bible.BibleBooksScreen
import com.aci.feature.bible.BibleChapterPickerScreen
import com.aci.feature.bible.BibleReaderScreen
import com.aci.feature.bible.BibleSearchScreen
import com.aci.feature.bible.PlanDayReaderScreen
import com.aci.feature.bible.ReadingPlanScreen
import com.aci.feature.bible.ReadingPlanViewModel
import com.aci.feature.bible.BibleViewModel
import com.aci.feature.events.EventDetailScreen
import com.aci.feature.events.EventsListScreen
import com.aci.feature.events.EventsViewModel
import com.aci.feature.home.HomeScreen
import com.aci.feature.prayer.PrayerViewModel
import com.aci.feature.prayer.PrayerWallScreen
import com.aci.feature.prayer.SubmitPrayerScreen
import com.aci.core.domain.enum.Language
import com.aci.core.domain.enum.Role
import com.aci.feature.admin.SongRequestAdminScreen
import com.aci.feature.admin.SongRequestAdminViewModel
import com.aci.feature.auth.AuthViewModel
import com.aci.feature.auth.LoginScreen
import com.aci.feature.auth.RegisterScreen
import com.aci.feature.profile.AboutScreen
import com.aci.feature.songs.SongDetailScreen
import com.aci.feature.songs.SongListScreen
import com.aci.feature.songs.SongRequestScreen
import com.aci.feature.songs.SongRequestViewModel
import com.aci.feature.songs.SongsViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            ACITheme {
                ACIApp()
            }
        }
    }
}

/** ACI's official YouTube channel — the sermon archive the Home card links out to. */
private const val ACI_YOUTUBE_CHANNEL_URL = "https://www.youtube.com/@theapostolicchurchofindia"

/** Hands the link to whatever app can open it; silently ignored if the device has none. */
private fun Context.openUrl(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    runCatching { startActivity(intent) }
        .onFailure { Timber.w(it, "No app available to open $url") }
}

private object Routes {
    const val HOME = "home"
    const val BIBLE = "bible"
    const val BIBLE_CHAPTERS = "bibleChapters/{bookId}"
    const val BIBLE_READER = "bibleReader/{bookId}/{chapter}"
    const val BIBLE_SEARCH = "bibleSearch"
    const val BIBLE_BOOKMARKS = "bibleBookmarks"
    const val READING_PLAN = "readingPlan"
    const val PLAN_DAY_READER = "planDayReader/{dayNumber}"
    const val SONGS = "songs"
    const val SONG_DETAIL = "songDetail/{songId}"
    const val SONG_REQUEST = "songRequest"
    const val SONG_REQUESTS_ADMIN = "songRequestsAdmin"
    const val EVENTS = "events"
    const val EVENT_DETAIL = "eventDetail/{eventId}"
    const val PRAYER = "prayer"
    const val SUBMIT_PRAYER = "submitPrayer"
    const val MY_ACI = "myaci"
    const val LOGIN = "login"
    const val REGISTER = "register"

    fun songDetail(songId: String) = "songDetail/$songId"
    fun bibleChapters(bookId: String) = "bibleChapters/$bookId"
    fun bibleReader(bookId: String, chapter: Int) = "bibleReader/$bookId/$chapter"
    fun eventDetail(eventId: String) = "eventDetail/$eventId"
    fun planDayReader(dayNumber: Int) = "planDayReader/$dayNumber"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ACIApp() {
    val navController = rememberNavController()
    val preferredBranch = ChurchContent.branches.first { it.id == BranchIds.BHARATH_NAGAR }
    val songRepository = ServiceLocator.songRepository
    val bibleRepository = ServiceLocator.bibleRepository
    val authViewModel: AuthViewModel = viewModel()
    val currentUser by authViewModel.currentUser.collectAsState()
    val adminRoles = setOf(Role.BRANCH_ADMIN, Role.ACI_ADMIN, Role.SUPER_ADMIN)
    val selectedLanguage by AppLanguageSession.current.collectAsState()
    val context = LocalContext.current

    // Single Room-backed source of truth for songs, shared by Home (weekly song lookup)
    // and the Songs tab — both read the same seeded database instead of static lists.
    val allSongs by remember { songRepository.observeSongs() }.collectAsState(initial = emptyList())

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = when {
        backStackEntry?.destination?.route?.startsWith("bible") == true -> Routes.BIBLE
        backStackEntry?.destination?.route?.startsWith("event") == true -> Routes.EVENTS
        // Reached from the My ACI screen, not the Songs tab — keep My ACI highlighted.
        backStackEntry?.destination?.route == Routes.SONG_REQUESTS_ADMIN -> Routes.MY_ACI
        backStackEntry?.destination?.route?.startsWith("song") == true -> Routes.SONGS
        else -> backStackEntry?.destination?.route?.substringBefore("/") ?: Routes.HOME
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ACITopBar(
                title = "ACI Church",
                subtitle = currentUser?.let { user ->
                    {
                        AppBarSignedInLine(
                            userName = user.fullName.ifBlank { user.email },
                            selectedLanguage = selectedLanguage,
                            onLogout = { authViewModel.logout() }
                        )
                    }
                },
                actions = {
                    AppBarActions(
                        selectedLanguage = selectedLanguage,
                        onLanguageSelected = { language -> AppLanguageSession.select(language) }
                    )
                }
            )
        },
        bottomBar = {
            ACIBottomNav(
                items = when (selectedLanguage) {
                    Language.TAMIL -> defaultBottomNavItemsTamil
                    Language.TELUGU -> defaultBottomNavItemsTelugu
                    Language.ENGLISH -> defaultBottomNavItems
                },
                currentRoute = currentRoute,
                onNavigate = { route ->
                    navController.navigateSingleTopTo(route)
                }
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Routes.HOME) {
                val todaysWeeklySong = ChurchContent.weeklySongs.firstOrNull { it.branchId == preferredBranch.id }
                val weeklySongDetails = todaysWeeklySong?.let { ws -> allSongs.firstOrNull { it.id == ws.songId } }
                val events by remember { ServiceLocator.eventRepository.observeEvents() }.collectAsState(initial = emptyList())

                HomeScreen(
                    preferredBranch = preferredBranch,
                    todayVerse = ChurchContent.todayVerse,
                    todayVerseVideo = ChurchContent.videosFor(ChurchContent.todayVerse.date).firstOrNull(),
                    weeklySong = todaysWeeklySong,
                    weeklySongDetails = weeklySongDetails,
                    latestSermon = ChurchContent.latestSermon,
                    upcomingEvent = events.minByOrNull { it.startAt ?: kotlinx.datetime.Instant.DISTANT_FUTURE },
                    currentUser = currentUser,
                    selectedLanguage = selectedLanguage,
                    onOpenSong = { songId -> navController.navigate(Routes.songDetail(songId)) },
                    onOpenPrayer = { navController.navigate(Routes.PRAYER) },
                    onOpenBible = { navController.navigateSingleTopTo(Routes.BIBLE) },
                    onOpenEvents = { navController.navigateSingleTopTo(Routes.EVENTS) },
                    onWatchSermons = { context.openUrl(ACI_YOUTUBE_CHANNEL_URL) },
                    onOpenLogin = { navController.navigate(Routes.LOGIN) },
                    onOpenRegister = { navController.navigate(Routes.REGISTER) },
                    onOpenAdminRequests = { navController.navigate(Routes.SONG_REQUESTS_ADMIN) }
                )
            }

            composable(Routes.LOGIN) {
                LoginScreen(
                    onBack = { navController.popBackStack() },
                    onLogin = { email, pin, onResult -> authViewModel.login(email, pin, onResult) },
                    onLoggedIn = { navController.popBackStack() },
                    onGoToRegister = { navController.navigate(Routes.REGISTER) { popUpTo(Routes.LOGIN) { inclusive = true } } }
                )
            }

            composable(Routes.REGISTER) {
                RegisterScreen(
                    onBack = { navController.popBackStack() },
                    onRegister = { fullName, email, phone, pin, onResult ->
                        authViewModel.register(fullName, email, phone, pin, onResult)
                    },
                    onRegistered = { navController.popBackStack() }
                )
            }

            composable(Routes.SONGS) {
                val songsViewModel: SongsViewModel = viewModel()
                val songs by songsViewModel.songs.collectAsState()
                if (songs.isEmpty()) {
                    ACILoading(message = "Loading song library…")
                } else {
                    SongListScreen(
                        songs = songs,
                        onSongClick = { song -> navController.navigate(Routes.songDetail(song.id)) },
                        onRequestSong = { navController.navigate(Routes.SONG_REQUEST) }
                    )
                }
            }

            composable(Routes.SONG_REQUEST) {
                val requestViewModel: SongRequestViewModel = viewModel()
                SongRequestScreen(
                    onBack = { navController.popBackStack() },
                    onSubmit = { titleEn, titleNative, language, notes ->
                        requestViewModel.submit(titleEn, titleNative, language, notes)
                    }
                )
            }

            composable(Routes.SONG_REQUESTS_ADMIN) {
                if (currentUser == null || currentUser?.role !in adminRoles) {
                    LoginScreen(
                        onBack = { navController.popBackStack() },
                        onLogin = { email, pin, onResult -> authViewModel.login(email, pin, onResult) },
                        onLoggedIn = {},
                        onGoToRegister = { navController.navigate(Routes.REGISTER) }
                    )
                } else {
                    val adminViewModel: SongRequestAdminViewModel = viewModel()
                    val pending by adminViewModel.pending.collectAsState()
                    SongRequestAdminScreen(
                        pending = pending,
                        signedInAsName = currentUser!!.fullName,
                        onBack = { navController.popBackStack() },
                        onApprove = { request -> adminViewModel.approve(request) },
                        onReject = { request -> adminViewModel.reject(request) },
                        onLogout = {
                            authViewModel.logout()
                            navController.popBackStack()
                        }
                    )
                }
            }

            composable(Routes.SONG_DETAIL) { entry ->
                val songId = entry.arguments?.getString("songId")
                val song by remember(songId) {
                    if (songId != null) songRepository.observeSong(songId) else flowOf(null)
                }.collectAsState(initial = null)
                val lyrics by remember(songId) {
                    if (songId != null) songRepository.observeLyrics(songId) else flowOf(emptyList())
                }.collectAsState(initial = emptyList())

                if (song != null) {
                    SongDetailScreen(
                        song = song!!,
                        lyricSections = lyrics,
                        onBack = { navController.popBackStack() }
                    )
                } else {
                    ACILoading(message = "Loading song…")
                }
            }

            // ---- Bible ----
            composable(Routes.BIBLE) {
                val bibleViewModel: BibleViewModel = viewModel()
                val books by bibleViewModel.books.collectAsState()
                if (books.isEmpty()) {
                    ACILoading(message = "Loading the Bible…")
                } else {
                    BibleBooksScreen(
                        books = books,
                        language = selectedLanguage,
                        onBookClick = { book -> navController.navigate(Routes.bibleChapters(book.id)) },
                        onSearchClick = { navController.navigate(Routes.BIBLE_SEARCH) },
                        onBookmarksClick = { navController.navigate(Routes.BIBLE_BOOKMARKS) },
                        onReadingPlanClick = { navController.navigate(Routes.READING_PLAN) }
                    )
                }
            }

            composable(Routes.BIBLE_CHAPTERS) { entry ->
                val bookId = entry.arguments?.getString("bookId")
                val bibleViewModel: BibleViewModel = viewModel()
                val books by bibleViewModel.books.collectAsState()
                val book = books.firstOrNull { it.id == bookId }
                if (book != null) {
                    BibleChapterPickerScreen(
                        book = book,
                        language = selectedLanguage,
                        onChapterClick = { chapter -> navController.navigate(Routes.bibleReader(book.id, chapter)) }
                    )
                } else {
                    ACILoading()
                }
            }

            composable(Routes.BIBLE_READER) { entry ->
                val bookId = entry.arguments?.getString("bookId") ?: return@composable
                val chapter = entry.arguments?.getString("chapter")?.toIntOrNull() ?: 1
                val bibleViewModel: BibleViewModel = viewModel()
                val books by bibleViewModel.books.collectAsState()
                val book = books.firstOrNull { it.id == bookId }
                val verses by remember(bookId, chapter) {
                    bibleRepository.observeVerses(bookId, chapter)
                }.collectAsState(initial = emptyList())
                val bookmarkedIds by bibleViewModel.bookmarkedVerseIds.collectAsState()
                var fontScale by remember { mutableStateOf(1f) }
                var noteTargetVerse by remember { mutableStateOf<com.aci.core.domain.model.BibleVerse?>(null) }

                if (book != null && verses.isNotEmpty()) {
                    BibleReaderScreen(
                        book = book,
                        chapter = chapter,
                        verses = verses,
                        language = selectedLanguage,
                        bookmarkedVerseIds = bookmarkedIds,
                        fontScale = fontScale,
                        onFontScaleToggle = { fontScale = if (fontScale >= 1.4f) 1f else fontScale + 0.2f },
                        onToggleBookmark = { verse -> bibleViewModel.toggleBookmark(verse.id) },
                        onAddNote = { verse -> noteTargetVerse = verse },
                        onBack = { navController.popBackStack() },
                        onPrevChapter = if (chapter > 1) {
                            { navController.navigate(Routes.bibleReader(bookId, chapter - 1)) { popUpTo(Routes.BIBLE_READER) { inclusive = true } } }
                        } else null,
                        onNextChapter = if (chapter < book.numChapters) {
                            { navController.navigate(Routes.bibleReader(bookId, chapter + 1)) { popUpTo(Routes.BIBLE_READER) { inclusive = true } } }
                        } else null,
                        onChapterRead = { bibleViewModel.markChapterRead(bookId, chapter) }
                    )

                    noteTargetVerse?.let { verse ->
                        AddNoteDialog(
                            verse = verse,
                            onDismiss = { noteTargetVerse = null },
                            onSave = { text ->
                                bibleViewModel.addNote(verse.id, text)
                                noteTargetVerse = null
                            }
                        )
                    }
                } else {
                    ACILoading()
                }
            }

            composable(Routes.BIBLE_SEARCH) {
                val bibleViewModel: BibleViewModel = viewModel()
                val books by bibleViewModel.books.collectAsState()
                val booksById = remember(books) { books.associateBy { it.id } }
                var query by remember { mutableStateOf("") }
                val results by remember(query) {
                    if (query.isBlank()) flowOf(emptyList()) else bibleRepository.searchVerses(query)
                }.collectAsState(initial = emptyList())

                BibleSearchScreen(
                    query = query,
                    onQueryChange = { query = it },
                    results = results,
                    booksById = booksById,
                    language = selectedLanguage,
                    onVerseClick = { verse ->
                        navController.navigate(Routes.bibleReader(verse.bookId, verse.chapter))
                    }
                )
            }

            composable(Routes.BIBLE_BOOKMARKS) {
                val bibleViewModel: BibleViewModel = viewModel()
                val books by bibleViewModel.books.collectAsState()
                val booksById = remember(books) { books.associateBy { it.id } }
                val bookmarkedVerses by remember {
                    bibleRepository.observeBookmarkedVerses(com.aci.data.content.CurrentUser.ID)
                }.collectAsState(initial = emptyList())

                BibleBookmarksScreen(
                    bookmarkedVerses = bookmarkedVerses,
                    booksById = booksById,
                    language = selectedLanguage,
                    onVerseClick = { verse -> navController.navigate(Routes.bibleReader(verse.bookId, verse.chapter)) }
                )
            }

            composable(Routes.READING_PLAN) {
                val bibleViewModel: BibleViewModel = viewModel()
                val planViewModel: ReadingPlanViewModel = viewModel()
                val books by bibleViewModel.books.collectAsState()
                val booksById = remember(books) { books.associateBy { it.id } }
                val planState by planViewModel.state.collectAsState()

                ReadingPlanScreen(
                    plan = planViewModel.plan,
                    state = planState,
                    booksById = booksById,
                    language = selectedLanguage,
                    today = planViewModel.today(),
                    isRegistered = currentUser != null,
                    onStart = { planViewModel.start() },
                    onSignIn = { navController.navigate(Routes.LOGIN) },
                    onOpenDay = { dayNumber -> navController.navigate(Routes.planDayReader(dayNumber)) },
                    onBack = { navController.popBackStack() }
                )
            }

            composable(Routes.PLAN_DAY_READER) { entry ->
                val dayNumber = entry.arguments?.getString("dayNumber")?.toIntOrNull() ?: return@composable
                val bibleViewModel: BibleViewModel = viewModel()
                val planViewModel: ReadingPlanViewModel = viewModel()
                val books by bibleViewModel.books.collectAsState()
                val booksById = remember(books) { books.associateBy { it.id } }
                val bookmarkedIds by bibleViewModel.bookmarkedVerseIds.collectAsState()
                val day = planViewModel.plan.firstOrNull { it.dayNumber == dayNumber }
                var fontScale by remember { mutableStateOf(1f) }
                var noteTargetVerse by remember { mutableStateOf<com.aci.core.domain.model.BibleVerse?>(null) }

                // One day can span more than one book, so each reading is its own range query.
                val verses by remember(dayNumber) {
                    if (day == null) flowOf(emptyList()) else combine(
                        day.readings.map { bibleRepository.observeVerseRange(it.bookId, it.startChapter, it.endChapter) }
                    ) { chunks -> chunks.toList().flatten() }
                }.collectAsState(initial = emptyList())

                if (day != null && verses.isNotEmpty()) {
                    PlanDayReaderScreen(
                        day = day,
                        verses = verses,
                        booksById = booksById,
                        language = selectedLanguage,
                        bookmarkedVerseIds = bookmarkedIds,
                        fontScale = fontScale,
                        onFontScaleToggle = { fontScale = if (fontScale >= 1.4f) 1f else fontScale + 0.2f },
                        onToggleBookmark = { verse -> bibleViewModel.toggleBookmark(verse.id) },
                        onAddNote = { verse -> noteTargetVerse = verse },
                        onChapterRead = { bookId, chapter -> bibleViewModel.markChapterRead(bookId, chapter) },
                        onBack = { navController.popBackStack() }
                    )

                    noteTargetVerse?.let { verse ->
                        AddNoteDialog(
                            verse = verse,
                            onDismiss = { noteTargetVerse = null },
                            onSave = { text ->
                                bibleViewModel.addNote(verse.id, text)
                                noteTargetVerse = null
                            }
                        )
                    }
                } else {
                    ACILoading()
                }
            }

            // ---- Events ----
            composable(Routes.EVENTS) {
                val eventsViewModel: EventsViewModel = viewModel()
                val events by eventsViewModel.events.collectAsState()
                EventsListScreen(
                    events = events,
                    onEventClick = { event -> navController.navigate(Routes.eventDetail(event.id)) }
                )
            }

            composable(Routes.EVENT_DETAIL) { entry ->
                val eventId = entry.arguments?.getString("eventId")
                val eventsViewModel: EventsViewModel = viewModel()
                val events by eventsViewModel.events.collectAsState()
                val event = events.firstOrNull { it.id == eventId }
                var confirmationId by remember { mutableStateOf<String?>(null) }

                if (event != null) {
                    EventDetailScreen(
                        event = event,
                        registeredConfirmationId = confirmationId,
                        onBack = { navController.popBackStack() },
                        onRegister = { name, phone, seats ->
                            eventsViewModel.register(event, name, phone, seats) { id -> confirmationId = id }
                        }
                    )
                } else {
                    ACILoading()
                }
            }

            // ---- Prayer ----
            composable(Routes.PRAYER) {
                val prayerViewModel: PrayerViewModel = viewModel()
                val active by prayerViewModel.activeRequests.collectAsState()
                val answered by prayerViewModel.answeredRequests.collectAsState()
                val testimonies by prayerViewModel.testimonies.collectAsState()

                PrayerWallScreen(
                    activeRequests = active,
                    answeredRequests = answered,
                    testimonies = testimonies,
                    onPrayFor = { request -> prayerViewModel.prayFor(request) },
                    onMarkAnswered = { request -> prayerViewModel.markAnswered(request) },
                    onSubmitPrayer = { navController.navigate(Routes.SUBMIT_PRAYER) }
                )
            }

            composable(Routes.SUBMIT_PRAYER) {
                val prayerViewModel: PrayerViewModel = viewModel()
                SubmitPrayerScreen(
                    onBack = { navController.popBackStack() },
                    onSubmit = { text, isAnonymous, isPrivate ->
                        prayerViewModel.submit(text, isAnonymous, isPrivate)
                        navController.popBackStack()
                    }
                )
            }

            composable(Routes.MY_ACI) {
                AboutScreen(about = ChurchContent.about)
            }
        }
    }
}

private fun NavHostController.navigateSingleTopTo(route: String) =
    navigate(route) {
        popUpTo(graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
