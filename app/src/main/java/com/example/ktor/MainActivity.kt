package com.example.ktor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.ktor.api.MainViewModel
import com.example.ktor.api.MainViewModelFactory
import com.example.ktor.api.Repository
import com.example.ktor.api.model.Authorization
import com.example.ktor.api.model.Music
import com.example.ktor.api.model.Registration
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.collect

@DelicateCoroutinesApi
class MainActivity : ComponentActivity() {
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = Repository()
        val factory = MainViewModelFactory(repository)
        mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        setContent {

            val musics = remember { mutableStateOf(listOf<Music>()) }

            mainViewModel.getMusic()
            lifecycleScope.launchWhenStarted {
                mainViewModel.responseMusic.collect {
                    musics.value = it
                }
            }

            mainViewModel.postRegistration(
                registration = Registration(
                    username = "KtorTesting",
                    email = "ktorTesting@.",
                    password = "ktorTesting"
                )
            )

            mainViewModel.postAuthorization(
                Authorization(
                    email = "ktorTesting@.",
                    password = "ktorTesting"
                )
            )

            mainViewModel.getUserInfo()

            LazyColumn(content = {
                items(musics.value){item ->
                    Text(
                        text = item.title.toString(),
                        modifier = Modifier.padding(5.dp)
                    )
                }
            })
        }
    }
}