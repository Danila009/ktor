package com.example.ktor.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Registration(
    val id:Int? = null,
    val username:String,
    val email:String,
    val password:String
)
