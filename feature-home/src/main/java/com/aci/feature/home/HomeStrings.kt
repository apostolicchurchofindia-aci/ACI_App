package com.aci.feature.home

import com.aci.core.domain.enum.Language

/**
 * UI labels for the Home dashboard, selected by the in-app language picker rather than the
 * device locale — the picker is what the user actually controls, and it also drives which
 * scripture/song text is shown.
 */
internal data class HomeStrings(
    val registerLogin: String,
    val accountBlurb: String,
    val register: String,
    val login: String,
    val accessSuffix: String,
    val songRequestsAdmin: String,
    val dailyVerse: String,
    val read: String,
    val share: String,
    val watchAciSermons: String,
    val playOnYouTube: String,
    val thisSundaysSong: String,
    val practiceSong: String,
    val upcomingService: String,
    val addReminder: String,
    val latestSermon: String,
    val watch: String,
    val prayer: String,
    val prayerPrompt: String,
    val submitPrayer: String,
    val events: String,
    val seeAllEvents: String
)

private val english = HomeStrings(
    registerLogin = "Register / Login",
    accountBlurb = "Create an account to save your prayer requests, bookmarks, and more.",
    register = "Register",
    login = "Login",
    accessSuffix = "access",
    songRequestsAdmin = "Song Requests (Admin)",
    dailyVerse = "Daily Bible Verse",
    read = "Read",
    share = "Share",
    watchAciSermons = "Watch ACI Sermons",
    playOnYouTube = "Play on YouTube",
    thisSundaysSong = "This Sunday's Song",
    practiceSong = "Practice Song",
    upcomingService = "Upcoming Service",
    addReminder = "Add Reminder",
    latestSermon = "Latest Sermon",
    watch = "Watch",
    prayer = "Prayer",
    prayerPrompt = "How can we pray for you?",
    submitPrayer = "Submit Prayer",
    events = "Events",
    seeAllEvents = "See All Events"
)

private val tamil = HomeStrings(
    registerLogin = "பதிவு / உள்நுழைவு",
    accountBlurb = "உங்கள் ஜெப விண்ணப்பங்கள், புத்தகக் குறிகள் மற்றும் பலவற்றைச் சேமிக்க ஒரு கணக்கை உருவாக்குங்கள்.",
    register = "பதிவு செய்",
    login = "உள்நுழை",
    accessSuffix = "அணுகல்",
    songRequestsAdmin = "பாடல் வேண்டுகோள்கள் (நிர்வாகி)",
    dailyVerse = "இன்றைய வேத வசனம்",
    read = "படிக்க",
    share = "பகிர",
    watchAciSermons = "ACI செய்திகளைக் காண",
    playOnYouTube = "YouTube-ல் பார்க்க",
    thisSundaysSong = "இந்த ஞாயிறு பாடல்",
    practiceSong = "பாடலைப் பயிற்சி செய்",
    upcomingService = "அடுத்த ஆராதனை",
    addReminder = "நினைவூட்டல் சேர்",
    latestSermon = "சமீபத்திய செய்தி",
    watch = "பார்க்க",
    prayer = "ஜெபம்",
    prayerPrompt = "உங்களுக்காக நாங்கள் எவ்வாறு ஜெபிக்கலாம்?",
    submitPrayer = "ஜெப விண்ணப்பம்",
    events = "நிகழ்ச்சிகள்",
    seeAllEvents = "அனைத்து நிகழ்ச்சிகளையும் காண"
)

private val telugu = HomeStrings(
    registerLogin = "నమోదు / లాగిన్",
    accountBlurb = "మీ ప్రార్థన అభ్యర్థనలు, బుక్‌మార్క్‌లు మరియు మరిన్నింటిని సేవ్ చేయడానికి ఖాతాను సృష్టించండి.",
    register = "నమోదు చేసుకోండి",
    login = "లాగిన్",
    accessSuffix = "యాక్సెస్",
    songRequestsAdmin = "పాటల అభ్యర్థనలు (నిర్వాహకుడు)",
    dailyVerse = "నేటి బైబిల్ వచనం",
    read = "చదవండి",
    share = "పంచుకోండి",
    watchAciSermons = "ACI సందేశాలు చూడండి",
    playOnYouTube = "YouTube లో చూడండి",
    thisSundaysSong = "ఈ ఆదివారం పాట",
    practiceSong = "పాటను అభ్యసించండి",
    upcomingService = "రాబోయే ఆరాధన",
    addReminder = "రిమైండర్ జోడించండి",
    latestSermon = "తాజా సందేశం",
    watch = "చూడండి",
    prayer = "ప్రార్థన",
    prayerPrompt = "మీ కోసం మేము ఎలా ప్రార్థించాలి?",
    submitPrayer = "ప్రార్థన అభ్యర్థన",
    events = "కార్యక్రమాలు",
    seeAllEvents = "అన్ని కార్యక్రమాలు చూడండి"
)

internal fun homeStringsFor(language: Language): HomeStrings = when (language) {
    Language.ENGLISH -> english
    Language.TAMIL -> tamil
    Language.TELUGU -> telugu
}
