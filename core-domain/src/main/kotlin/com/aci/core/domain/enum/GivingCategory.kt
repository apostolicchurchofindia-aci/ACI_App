package com.aci.core.domain.enum

import kotlinx.serialization.Serializable

@Serializable
enum class GivingCategory {
    TITHE,
    OFFERING,
    DONATION,
    MISSIONS,
    BUILDING_FUND,
    YOUTH_MINISTRY,
    KIDS_MINISTRY,
    SUNDAY_SCHOOL,
    WORSHIP_MINISTRY,
    COMMUNITY_OUTREACH,
    EMERGENCY_RELIEF,
    SCHOLARSHIP,
    OTHER
}
