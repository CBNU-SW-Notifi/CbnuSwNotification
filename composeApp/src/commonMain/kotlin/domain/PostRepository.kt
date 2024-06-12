package domain

interface PostRepository {
    suspend fun fetchJobList(page: Int, size: Int): List<Post>
}