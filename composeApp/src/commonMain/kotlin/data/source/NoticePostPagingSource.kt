package data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import data.model.Post
import data.repositories.DeptNotificationListRepository
import kotlinx.coroutines.flow.first

class NoticePostPagingSource(private val deptNotificationListRepository: DeptNotificationListRepository) : PagingSource<Int, Post>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        val page = params.key ?: 0
        return try {
            val response = deptNotificationListRepository.fetchNoticePostData(page, params.loadSize).first()
            val data = response.data
            //val data = response.data.sortedByDescending { it.postId }
            LoadResult.Page(
                data = data,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (data.isEmpty() || page >= response.pagination.totalPage) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}