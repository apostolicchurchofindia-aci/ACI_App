package com.aci.data.content

/** Stable id constants used to reference seed records from code (navigation args, previews, lookups). */
object BranchIds {
    const val BHARATH_NAGAR = "branch-bharath-nagar"
    const val MANNURPET = "branch-mannurpet"
    const val PONNERI = "branch-ponneri"
}

object SongIds {
    const val AMAZING_GRACE = "song-amazing-grace"
    const val TEN_THOUSAND_REASONS = "song-10000-reasons"
    const val HOW_GREAT_THOU_ART = "song-how-great-thou-art"
    const val GOODNESS_OF_GOD = "song-goodness-of-god"
    const val BUILD_MY_LIFE = "song-build-my-life"
    const val AAYANE_NAA_SANGEETHAMU = "song-aayane-naa-sangeethamu"
}

/** No auth flow exists yet — every screen acts as this seeded Member account. */
object CurrentUser {
    const val ID = "user-member"
}
