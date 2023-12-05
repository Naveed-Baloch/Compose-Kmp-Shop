package network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import network.dto.ProductItem
import network.utils.ApiRoutes

class ShopRepo(private val httpClient: HttpClient) : ResRepo() {

    suspend fun getProducts() = loadResourceFlow {
        httpClient.get(ApiRoutes.BASE_URL + ApiRoutes.product).body<List<ProductItem>>()
    }
}