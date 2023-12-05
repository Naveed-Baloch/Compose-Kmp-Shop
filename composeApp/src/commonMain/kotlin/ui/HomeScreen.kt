package ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import network.Result
import network.ShopRepo

@Composable
fun HomeScreen() {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }
    val repo = ShopRepo(client)
    val scope = rememberCoroutineScope()
    var text by remember { mutableStateOf("Loading") }
    LaunchedEffect(true) {
        scope.launch {
            repo.getProducts().collect {
                text = when (it) {
                    is Result.Error -> it.message.toString()
                    is Result.Loading -> "Loading"
                    is Result.Success -> {
                        val products = it.data
                        it.data.toString()
                    }
                }
            }
        }
    }
    GreetingView(text)
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)

}