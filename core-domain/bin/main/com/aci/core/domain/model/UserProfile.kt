package com.aci.core.domain.model

import com.aci.core.domain.enum.Language
import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate

@Serializable
data class UserProfile(
    val userId: String,
    val avatarUrl: String = "",
    val preferredBranchId: String = "",
    val language: Language = Language.ENGLISH,
    val bio: String = "",
    val dob: LocalDate? = null,
    val anniversary: LocalDate? = null
)
