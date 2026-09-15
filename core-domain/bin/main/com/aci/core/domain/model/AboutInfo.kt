package com.aci.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AboutInfo(
    val foundedYear: Int,
    val foundingCity: String,
    val foundingStory: String = "",
    val tagline: String,
    val missionStatement: String,
    val coreBeliefs: List<CoreBelief> = emptyList(),
    val ministries: List<String> = emptyList(),
    /** Ministry locations named on the church's own site beyond the 3 branches with full service-time detail. */
    val additionalLocations: List<String> = emptyList(),
    val founderName: String = "",
    val founderTitle: String = "",
    val coLeaderName: String = "",
    val coLeaderTitle: String = "",
    val instagramHandle: String = "",
    val youtubeHandle: String = "",
    val websiteUrl: String = ""
)

@Serializable
data class CoreBelief(
    val title: String,
    val description: String
)
