package domain

import data.model.Post

interface PostRepository {
    suspend fun fetchJobList(page: Int, size: Int): List<Post>
}