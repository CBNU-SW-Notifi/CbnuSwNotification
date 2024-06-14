package presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntOffset
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
import kotlin.math.roundToInt

@Composable
fun PostDetailScreen(postId: Int, navController: NavController) {
    val viewModel: PostDetailViewModel = getKoin().get()
    val postDetail by viewModel.postDetail.collectAsState()

    LaunchedEffect(postId) {
        viewModel.getPostDetail(postId)
    }

    val context = LocalPlatformContext.current
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    val minScale = 1f
    val maxScale = 3f

    postDetail?.let { detail ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        scale = (scale * zoom).coerceIn(minScale, maxScale)

                        val maxX = (size.width * (scale - 1)) / (2 * scale)
                        val maxY = (size.height * (scale - 1)) / (2 * scale)

                        offsetX = (offsetX + pan.x * scale).coerceIn(-maxX, maxX)
                        offsetY = (offsetY + pan.y * scale).coerceIn(-maxY, maxY)
                    }
                }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .scale(scale)
                    .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) },
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                item {
                    Text(
                        text = "Title: ${detail.title}",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
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

                            HorizontalDivider(color = Color.LightGray, thickness = 1.dp)

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
        }
    } ?: run {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}