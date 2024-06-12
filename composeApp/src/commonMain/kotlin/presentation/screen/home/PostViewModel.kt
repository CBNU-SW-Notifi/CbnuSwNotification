package presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.PagingData
import app.cash.paging.cachedIn
import domain.Post
import domain.PostRepositoryProvider
import kotlinx.coroutines.flow.Flow

class PostViewModel(private val repositoryProvider: PostRepositoryProvider) : ViewModel() {
    val posts: Flow<PagingData<Post>> = repositoryProvider.getPosts().cachedIn(viewModelScope)
}