package presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import app.cash.paging.cachedIn
import data.model.Post
import data.repositories.DeptNotificationListRepository
import data.source.JobPostPagingSource
import data.repositories.JobHuntRepository
import data.source.NoticePostPagingSource
import kotlinx.coroutines.flow.Flow

class HomeViewModel(
    private val jobHuntRepository: JobHuntRepository,
    private val deptNotificationListRepository: DeptNotificationListRepository
) : ViewModel() {

    val pagingJobDataFlow: Flow<PagingData<Post>> =
        Pager(PagingConfig(pageSize = 10, initialLoadSize = 10)) {
            JobPostPagingSource(jobHuntRepository)
        }.flow.cachedIn(viewModelScope)

    val pagingNoticeDataFlow: Flow<PagingData<Post>> =
        Pager(PagingConfig(pageSize = 10, initialLoadSize = 10)) {
            NoticePostPagingSource(deptNotificationListRepository)
        }.flow.cachedIn(viewModelScope)
}