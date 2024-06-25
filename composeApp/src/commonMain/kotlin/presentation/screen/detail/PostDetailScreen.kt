package presentation.screen.detail

import ArrowBack
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.LocalPlatformContext
import getOpenUrl
import org.koin.compose.getKoin
import presentation.components.LoadingAnimation
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailScreen(postId: Int, tabTitle: String, navController: NavController) {
    val viewModel: PostDetailViewModel = getKoin().get()
    val postDetail by viewModel.jobHuntDetail.collectAsState()

    LaunchedEffect(postId) {
        viewModel.getPostDetail(postId)
    }

    val context = LocalPlatformContext.current
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    val minScale = 1f
    val maxScale = 3f
    val panSpeedFactor = 0.5f

    /*val notifier = NotifierManager.getLocalNotifier()
    notifier.notify("이혁수 힘내라!!", "헤헤 방귀 뿡뿡")*/

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .background(Color.White)
            ) {
                Box(
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth()
                ) {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                tabTitle, style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFFBC2055),
                                )
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = { navController.navigateUp() }) {
                                Icon(
                                    painter = rememberVectorPainter(
                                        image = ArrowBack
                                    ),
                                    contentDescription = "Back",
                                    tint = Color(0xFFBC2055)
                                )
                            }
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Color.White
                        )
                    )
                }
                HorizontalDivider(
                    modifier = Modifier.padding(top = 9.dp),
                    color = Color(0xFFBC2055),
                    thickness = 1.5.dp
                )
            }
        }
    ) { paddingValues ->
        postDetail?.let { detail ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.White)
                    .pointerInput(Unit) {
                        detectTransformGestures { _, pan, zoom, _ ->
                            scale = (scale * zoom).coerceIn(minScale, maxScale)

                            val maxX = (size.width * (scale - 1)) / (2 * scale)
                            val maxY = (size.height * (scale - 1)) / (2 * scale)

                            offsetX = (offsetX + pan.x * panSpeedFactor).coerceIn(-maxX, maxX)
                            offsetY = (offsetY + pan.y * panSpeedFactor).coerceIn(-maxY, maxY)
                        }
                    }
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .scale(scale)
                        .padding(bottom = 4.dp)
                        .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) },
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    item {
                        Text(
                            modifier = Modifier
                                .padding(start = 18.dp, top = 22.dp, end = 18.dp),
                            text = detail.title,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF222222)
                        )

                        Text(
                            modifier = Modifier
                                .padding(start = 18.dp, top = 4.dp, end = 18.dp),
                            //TODO : postId 삭제
                            text = "#${detail.postId} | ${detail.createTime}",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF888888),
                            )
                        )
                    }

                    item {
                        Text(
                            modifier = Modifier
                                .padding(start = 18.dp, top = 14.dp, end = 18.dp, bottom = 18.dp),
                            text = detail.content,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF444444),
                            )
                        )
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
                                    modifier = Modifier
                                        .fillMaxWidth(),
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
                            }
                        }
                    }

                    detail.attachedFiles?.let { files ->
                        if (files.isNotEmpty()) {
                            item {
                                HorizontalDivider(
                                    modifier = Modifier
                                        .padding(top = 18.dp),
                                    color = Color.LightGray,
                                    thickness = 1.dp
                                )
                            }
                        }
                        itemsIndexed(files) { index, attachedFile ->
                            if (attachedFile.name.isNotBlank()) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 7.dp, start = 18.dp)
                                ) {
                                    Text(
                                        text = "첨부 파일 ${index + 1} : ",
                                        style = TextStyle(
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight(500),
                                            color = Color(0xFF000000),
                                            letterSpacing = 0.65.sp
                                        )
                                    )
                                    Text(
                                        text = attachedFile.name,
                                        modifier = Modifier.clickable {
                                            getOpenUrl(context).open(attachedFile.url)
                                        },
                                        style = TextStyle(
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight(500),
                                            color = Color(0xFF666666),
                                            textDecoration = TextDecoration.Underline
                                        )
                                    )
                                }
                            }
                        }
                        if (files.isNotEmpty()) {
                            item {
                                HorizontalDivider(
                                    modifier = Modifier
                                        .padding(top = 7.dp, bottom = 24.dp),
                                    color = Color.LightGray,
                                    thickness = 1.dp
                                )
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
}