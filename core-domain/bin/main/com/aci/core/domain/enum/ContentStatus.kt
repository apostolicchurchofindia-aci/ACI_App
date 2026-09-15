package com.aci.core.domain.enum

import kotlinx.serialization.Serializable

@Serializable
enum class ContentStatus {
    DRAFT,
    REVIEW,
    APPROVED,
    SCHEDULED,
    PUBLISHED,
    ARCHIVED
}
