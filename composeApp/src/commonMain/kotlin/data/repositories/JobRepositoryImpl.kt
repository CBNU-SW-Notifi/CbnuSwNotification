package data.repositories

import NetworkConstants
import data.model.JobModel
import domain.JobRepository
import domain.Post
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.isSuccess
import kotlinx.serialization.json.Json

class JobRepositoryImpl(private val httpClient: HttpClient) : JobRepository {
    override suspend fun fetchJobList(page: Int, size: Int): List<Post> {
        val response = httpClient.get(NetworkConstants.BASE_URL) {
            parameter("page", page)
            parameter("size", size)
        }
        if (response.status.isSuccess()) {
            val responseBody: String = response.body()
            val jobModel: JobModel = Json.decodeFromString(responseBody)
            return jobModel.data.map {
                Post(
                    postId = it.postId,
                    title = it.title,
                    createTime = it.createTime
                )
            }
        } else {
            throw Exception("Failed to fetch posts: ${response.status.description}")
        }
    }
}