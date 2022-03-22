package com.example.ktor.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Authorization(
    val email:String,
    val password:String
)