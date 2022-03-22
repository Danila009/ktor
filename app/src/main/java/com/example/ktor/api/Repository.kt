package com.example.ktor.api

import android.util.Log
import com.example.ktor.api.model.*
import com.example.ktor.api.utils.ConstantsUrl.AUTHORIZATION_URL
import com.example.ktor.api.utils.ConstantsUrl.BASE_URL
import com.example.ktor.api.utils.ConstantsUrl.MUSIC_URL
import com.example.ktor.api.utils.ConstantsUrl.REGISTRATION_URL
import com.example.ktor.api.utils.ConstantsUrl.USER_INFO_URL
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*

class Repository {

    private var token = ""

    private val client = HttpClient(Android){
        install(JsonFeature){
            serializer = KotlinxSerializer()
        }
    }

    suspend fun getMusic(): List<Music> {
        return client.get(BASE_URL + MUSIC_URL){
            parameter("search", "a")
        }
    }

    suspend fun postRegistration(registration: Registration){
        val data = client.post<String>(
            BASE_URL + REGISTRATION_URL
        ){
            contentType(ContentType.Application.Json)
            body = registration
        }
        Log.e("Ktor:", data)
    }

    suspend fun postAuthorization(authorization: Authorization){
        val data = client.post<Header>(
            BASE_URL + AUTHORIZATION_URL
        ){
            contentType(ContentType.Application.Json)
            body = authorization
        }
        token = data.access_token
        Log.e("Ktor:", data.access_token)
    }

    suspend fun getUserInfo():User{
        return client.get(BASE_URL + USER_INFO_URL){
            if (token.isNotEmpty()){
                header("Authorization", "Bearer $token")
            }
        }
    }
}