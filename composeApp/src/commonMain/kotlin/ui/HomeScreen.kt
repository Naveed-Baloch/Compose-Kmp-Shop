package ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import network.Result
import network.ShopRepo
import org.koin.compose.koinInject

@Composable
fun HomeScreen(repo: ShopRepo = koinInject()) {
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