package data.repositories

import NetworkConstants
import data.model.ResponseData
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class JobHuntRepository(private val client: HttpClient) {
    private val json = Json { ignoreUnknownKeys = true }
    suspend fun fetchData(page: Int, size: Int): Flow<ResponseData> = flow {
        val response: HttpResponse = client.get(NetworkConstants.BASE_URL + "/api/v1/information-post/list") {
            parameter("page", page)
            parameter("size", size)
        }

        val responseData = response.bodyAsText().let {
            json.decodeFromString<ResponseData>(it)
        }

        emit(responseData)
    }
}
