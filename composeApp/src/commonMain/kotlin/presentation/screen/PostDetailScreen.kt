package presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
        LazyColumn {
            item {
                Text(text = "Title: ${detail.title}")
                Text(text = "Content: ${detail.content}")
            }
            items(detail.imageUrls) { imageUrl ->
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
            item {
                Text(text = "Attached Files:")
            }
            items(detail.attachedFiles) { attachedFile ->
                Text(text = "Name: ${attachedFile.name}")
                Text(text = "URL: ${attachedFile.url}")
            }
        }
    }
}