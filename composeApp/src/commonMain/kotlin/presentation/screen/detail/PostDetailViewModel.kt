package presentation.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.model.job_hunt.JobHuntDetail
import data.repositories.JobHuntRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PostDetailViewModel(private val jobHuntRepository: JobHuntRepository) : ViewModel() {

    private val _jobHuntDetail = MutableStateFlow<JobHuntDetail?>(null)
    val jobHuntDetail: StateFlow<JobHuntDetail?> = _jobHuntDetail

    fun getPostDetail(postId: Int) {
        viewModelScope.launch {
            jobHuntRepository.fetchJobHuntPostDetail(postId).collectLatest {
                _jobHuntDetail.value = it
            }
        }
    }
}