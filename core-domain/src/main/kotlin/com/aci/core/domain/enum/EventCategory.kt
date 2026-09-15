package com.aci.core.domain.enum

import kotlinx.serialization.Serializable

@Serializable
enum class EventCategory {
    WORSHIP_SERVICE,
    PRAYER_MEETING,
    BIBLE_STUDY,
    FELLOWSHIP,
    OUTREACH,
    CONFERENCE,
    SEMINAR,
    YOUTH_EVENT,
    KIDS_EVENT,
    WEDDING,
    FUNERAL,
    BAPTISM,
    COMMUNITY_SERVICE,
    FUNDRAISER,
    OTHER
}
