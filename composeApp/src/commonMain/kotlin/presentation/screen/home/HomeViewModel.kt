package presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import app.cash.paging.cachedIn
import data.model.Post
import data.source.PostPagingSource
import data.repositories.JobHuntRepository
import kotlinx.coroutines.flow.Flow

class HomeViewModel(private val jobHuntRepository: JobHuntRepository) : ViewModel() {

    val pagingDataFlow: Flow<PagingData<Post>> = Pager(PagingConfig(pageSize = 10, initialLoadSize = 10)) {
        PostPagingSource(jobHuntRepository)
    }.flow.cachedIn(viewModelScope)
}