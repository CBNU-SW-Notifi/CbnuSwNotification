package presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.model.job_hunt.PostDetail
import data.repositories.JobHuntRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PostDetailViewModel(private val jobHuntRepository: JobHuntRepository) : ViewModel() {

    private val _postDetail = MutableStateFlow<PostDetail?>(null)
    val postDetail: StateFlow<PostDetail?> = _postDetail

    fun getPostDetail(postId: Int) {
        viewModelScope.launch {
            jobHuntRepository.fetchPostDetail(postId).collectLatest {
                _postDetail.value = it
            }
        }
    }
}