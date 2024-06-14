package presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import getOpenUrl
import org.koin.compose.getKoin
import presentation.components.LoadingAnimation
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
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Text(
                    text = "Title: ${detail.title}",
                    style = MaterialTheme.typography.headlineMedium
                )

                HorizontalDivider(color = Color.Gray, thickness = 1.dp)
            }

            detail.imageUrls?.let { urls ->
                items(urls) { imageUrl ->
                    if (imageUrl.isNotBlank()) {
                        var alpha by remember { mutableStateOf(1f) }
                        var showLoadingAnimation by remember { mutableStateOf(true) }

                        AsyncImage(
                            model = imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxWidth(),
                            onState = { state ->
                                when (state) {
                                    is AsyncImagePainter.State.Loading -> {
                                        showLoadingAnimation = true
                                        alpha = 0.5f
                                    }
                                    is AsyncImagePainter.State.Success -> {
                                        showLoadingAnimation = false
                                        alpha = 1f
                                    }
                                    is AsyncImagePainter.State.Error -> {
                                        showLoadingAnimation = false
                                        alpha = 1f
                                    }
                                    else -> {}
                                }
                            }
                        )

                        if (showLoadingAnimation) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = 200.dp)
                            ) {
                                LoadingAnimation()
                            }
                        }

                        Image(
                            painter = rememberAsyncImagePainter(imageUrl),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .alpha(alpha),
                            contentScale = ContentScale.Crop
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

                        HorizontalDivider(color = Color.Gray, thickness = 1.dp)

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