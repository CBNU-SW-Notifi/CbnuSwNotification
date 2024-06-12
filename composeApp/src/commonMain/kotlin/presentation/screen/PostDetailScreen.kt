package presentation.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import org.koin.compose.getKoin
import presentation.screen.home.PostDetailViewModel

@Composable
fun PostDetailScreen(postId: Int, navController: NavController) {
    val viewModel: PostDetailViewModel = getKoin().get()
    val postDetail by viewModel.postDetail.collectAsState()

    viewModel.getPostDetail(postId)

    postDetail?.let { detail ->
        Text(text = "Title: ${detail.title}")
        Text(text = "Content: ${detail.content}")
        // More fields can be displayed as needed
    }
}