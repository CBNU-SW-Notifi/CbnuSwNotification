package presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.cash.paging.compose.collectAsLazyPagingItems
import org.koin.compose.getKoin
import presentation.screen.home.component.PostItem

@Composable
fun JobInfoScreen(navController: NavController) {
    val viewModel: HomeViewModel = getKoin().get()
    val posts = viewModel.pagingDataFlow.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .background(color = Color(0xFFF7F7F7)),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(count = posts.itemCount) { index ->
            val post = posts[index]
            post?.let {
                PostItem(
                    post = it,
                    onClick = {
                        navController.navigate("postDetail/${it.postId}/취업 정보")
                    }
                )
            }
        }
    }
}