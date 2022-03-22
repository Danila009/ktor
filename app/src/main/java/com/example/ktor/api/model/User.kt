package com.example.ktor.api.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username:String,
    val email:String,
    val password:String,
    val role:String
)
