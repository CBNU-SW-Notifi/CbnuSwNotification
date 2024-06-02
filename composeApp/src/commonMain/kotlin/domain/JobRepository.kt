package domain

import data.model.JobModel

interface JobRepository {
    suspend fun fetchJobList(page: Int, size: Int): List<Post>
}