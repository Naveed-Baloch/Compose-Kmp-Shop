package network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import network.utils.ApiUtils

class ShopRepo(private val httpClient: HttpClient) : ResRepo() {

    suspend fun getProduct() = loadResourceFlow {
        httpClient.get(ApiUtils.BASE_URL + ApiUtils.product)
    }
}