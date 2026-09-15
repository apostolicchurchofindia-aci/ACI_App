package com.aci.core.domain.enum

import kotlinx.serialization.Serializable

@Serializable
enum class Role {
    GUEST,
    MEMBER,
    SS_STUDENT,
    SS_TEACHER,
    WORSHIP_MEMBER,
    WORSHIP_LEADER,
    PASTOR,
    BRANCH_ADMIN,
    ACI_ADMIN,
    SUPER_ADMIN
}
