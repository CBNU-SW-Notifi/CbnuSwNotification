package domain

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import data.paging.PostPagingSource
import kotlinx.coroutines.flow.Flow

class PostRepositoryProvider(private val repository: PostRepository) {
    fun getPosts(): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(pageSize = 3),
            pagingSourceFactory = { PostPagingSource(repository) }
        ).flow
    }
}