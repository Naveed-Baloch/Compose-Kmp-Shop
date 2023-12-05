package ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import network.Result
import network.ShopRepo
import network.dto.ProductItem
import org.koin.compose.koinInject

@Composable
fun MainScreen(repo: ShopRepo = koinInject()) {
    var screenView by rememberSaveable { mutableStateOf(ScreenView.Splash) }
    var isLoading by rememberSaveable { mutableStateOf(false) }
    var products by rememberSaveable { mutableStateOf<List<ProductItem>>(emptyList()) }
    var isError by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(isError) {
        repo.getProducts().collect {
            isLoading = it is Result.Loading
            isError = it is Result.Error
            if(it is Result.Success){
                screenView = ScreenView.Home
                products = it.data?.toMutableList() ?: emptyList()
            }
        }
    }
    when (screenView) {
        ScreenView.Splash -> SplashView(isError = isError, onRetry = { isError = false})
        ScreenView.Home -> HomeScreen(products = products)
    }
}

enum class ScreenView {
    Splash,
    Home
}