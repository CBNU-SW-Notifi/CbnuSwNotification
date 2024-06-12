package presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.cash.paging.compose.collectAsLazyPagingItems
import data.model.Post
import org.koin.compose.getKoin

@Composable
fun MainScreen(navController: NavController) {
    val viewModel: MainViewModel = getKoin().get()
    val posts = viewModel.pagingDataFlow.collectAsLazyPagingItems()

    LazyColumn {
        items(count = posts.itemCount) { index ->
            val post = posts[index]
            post?.let {
                PostItem(post = it, onClick = { navController.navigate("postDetail/${it.postId}") })
            }
        }
    }
}

@Composable
fun PostItem(post: Post, onClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(
            text = post.title,
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
            modifier = Modifier.clickable(onClick = onClick)
        )
        Spacer(modifier = Modifier.height(4.dp))
        //Text(text = "Created: ${post.createTime}")
    }
}