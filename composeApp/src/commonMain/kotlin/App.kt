import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import di.appModule
import org.koin.compose.KoinApplication
import ui.HomeScreen

@Composable
fun App() {
    KoinApplication(application = { modules(appModule) }) {
        MaterialTheme {
            HomeScreen()
        }
    }
}