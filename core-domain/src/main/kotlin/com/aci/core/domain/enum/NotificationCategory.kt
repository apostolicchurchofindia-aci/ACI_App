package com.aci.core.domain.enum

import kotlinx.serialization.Serializable

@Serializable
enum class NotificationCategory {
    GENERAL,
    DAILY_VERSE,
    EVENT,
    PRAYER,
    WORSHIP,
    SERMON,
    SONG,
    GIVING,
    ANNOUNCEMENT,
    REMINDER,
    BIBLE_STUDY,
    SUNDAY_SCHOOL,
    ADMIN,
    COMMUNITY,
    SYSTEM
}
