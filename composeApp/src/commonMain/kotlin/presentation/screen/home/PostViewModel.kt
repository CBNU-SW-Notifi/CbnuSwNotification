package presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.JobRepository
import domain.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel(private val postRepository: JobRepository) : ViewModel() {
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> get() = _posts

    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> get() = _currentPage

    fun loadPosts(page: Int, size: Int) {
        viewModelScope.launch {
            try {
                val newPosts = postRepository.fetchJobList(page, size)
                _posts.value += newPosts
                _currentPage.value = page
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}