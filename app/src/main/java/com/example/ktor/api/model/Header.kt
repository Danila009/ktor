package com.example.ktor.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Header(
    val access_token:String,
    val username:String,
    val role:String
)