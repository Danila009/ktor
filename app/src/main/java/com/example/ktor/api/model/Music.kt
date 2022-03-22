package com.example.ktor.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Music(
    val date: String?,
    val document: String?,
    val id: Int?,
    val musicUrl: String?,
    val title: String?,
    val webIcon: String?
)