package data.repositories

import NetworkConstants
import data.model.ResponseData
import data.model.job_hunt.JobHuntDetail
import database.job_hunt.PostDetailDao
import database.toDomain
import database.toEntity
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class JobHuntRepository(
    private val client: HttpClient,
    private val postDetailDao: PostDetailDao
) {
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun fetchData(page: Int, size: Int): Flow<ResponseData> = flow {
        val response: HttpResponse =
            client.get(NetworkConstants.BASE_URL + "/api/v1/job-hunt-post/list") {
                parameter("page", page)
                parameter("size", size)
            }

        val responseData = response.bodyAsText().let {
            json.decodeFromString<ResponseData>(it)
        }

        emit(responseData)
    }

    suspend fun fetchJobHuntPostDetail(postId: Int): Flow<JobHuntDetail> = flow {
        var jobHuntEntity = postDetailDao.getPostDetail(postId)
        if (jobHuntEntity == null) {
            val response: HttpResponse =
                client.get("${NetworkConstants.BASE_URL}/api/v1/job-hunt-post/read/$postId")
            val jobHuntDetail = response.bodyAsText().let {
                json.decodeFromString<JobHuntDetail>(it)
            }
            jobHuntEntity = jobHuntDetail.toEntity()
            postDetailDao.insertPostDetail(jobHuntEntity)
        }
        emit(jobHuntEntity.toDomain())
    }
}