package com.example.ktor.api

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktor.api.model.Authorization
import com.example.ktor.api.model.Music
import com.example.ktor.api.model.Registration
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(
    private val repository: Repository
):ViewModel() {

    private val _responseMusic = MutableStateFlow(listOf<Music>())
    val responseMusic = _responseMusic.asStateFlow()

    fun getMusic(){
        viewModelScope.launch {
            _responseMusic.value = repository.getMusic()
        }
    }

    fun postRegistration(registration: Registration){
        viewModelScope.launch {
            try {
                repository.postRegistration(registration)
            }catch (e:Exception){
                Log.e("Retrofit:", e.message.toString())
            }
        }
    }

    fun postAuthorization(authorization: Authorization){
        viewModelScope.launch {
            repository.postAuthorization(authorization)
        }
    }

    fun getUserInfo(){
        viewModelScope.launch {
            try {
                delay(1000L)
                val response = repository.getUserInfo()
                Log.e("Ktor:", response.toString())
            }catch (e:Exception){
                Log.e("Ktor:", e.message.toString())
            }
        }
    }
}