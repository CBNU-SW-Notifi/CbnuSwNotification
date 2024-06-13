package presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import org.koin.compose.getKoin
import presentation.screen.home.PostDetailViewModel

@Composable
fun PostDetailScreen(postId: Int, navController: NavController) {
    val viewModel: PostDetailViewModel = getKoin().get()
    val postDetail by viewModel.postDetail.collectAsState()

    viewModel.getPostDetail(postId)

    postDetail?.let { detail ->
        Column {
            Text(text = "Title: ${detail.title}")
            Text(text = "Content: ${detail.content}")
            detail.imageUrls.forEach { imageUrl ->
                val imageUri = if (imageUrl.startsWith("data:image")) {
                    imageUrl
                } else {
                    imageUrl
                }
                Image(
                    painter = rememberAsyncImagePainter(imageUri),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
        }

    }
}