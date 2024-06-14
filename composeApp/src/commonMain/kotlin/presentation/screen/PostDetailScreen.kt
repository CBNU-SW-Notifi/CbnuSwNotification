package presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.rememberAsyncImagePainter
import getOpenUrl
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.getKoin
import presentation.screen.home.PostDetailViewModel

@Composable
fun PostDetailScreen(postId: Int, navController: NavController) {
    val viewModel: PostDetailViewModel = getKoin().get()
    val postDetail by viewModel.postDetail.collectAsState()

    LaunchedEffect(postId) {
        viewModel.getPostDetail(postId)
    }

    val context = LocalPlatformContext.current

    postDetail?.let { detail ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Text(
                    text = "Title: ${detail.title}",
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            detail.imageUrls?.let { urls ->
                items(urls) { imageUrl ->
                    if (imageUrl.isNotBlank()) {
                        coil3.compose.AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth(),
                            model = imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,

                            onError = {
                                println("load error")
                            },
                            onLoading = {
                                println("load onLoading")
                            },
                            onSuccess = {
                                println("load onSuccess")
                            },
                        )
                    }
                }
            }

            item {
                Text(
                    text = "Content: ${detail.content}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            detail.attachedFiles?.let { files ->
                itemsIndexed(files) { index, attachedFile ->
                    if (attachedFile.name.isNotBlank()) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "첨부 파일 ${index + 1} : ",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = attachedFile.name,
                                color = Color.Blue,
                                modifier = Modifier.clickable {
                                    getOpenUrl(context).open(attachedFile.url)
                                },
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    } ?: run {
        // Handle the loading or error state if needed
        Text(text = "Loading...", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
    }
}