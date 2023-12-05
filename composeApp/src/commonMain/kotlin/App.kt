import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import io.ktor.client.*
import io.ktor.client.statement.*
import kotlinx.coroutines.launch
import network.Result
import network.ShopRepo

@Composable
fun App() {
    val client = HttpClient()
    val repo = ShopRepo(client)
    MaterialTheme {
        val scope = rememberCoroutineScope()
        var text by remember { mutableStateOf("Loading") }
        LaunchedEffect(true) {
            scope.launch {
                repo.getProduct().collect {
                    text = when (it) {
                        is Result.Error -> it.message.toString()
                        is Result.Loading -> "Loading"
                        is Result.Success -> it.data?.bodyAsText().orEmpty()
                    }
                }
            }
        }
        GreetingView(text)
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}