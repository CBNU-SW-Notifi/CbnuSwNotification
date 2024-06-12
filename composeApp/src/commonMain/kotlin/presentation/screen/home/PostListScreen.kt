package presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.collectAsLazyPagingItems
import data.model.Post
import org.koin.compose.getKoin

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = getKoin().get()
    val posts = viewModel.pagingDataFlow.collectAsLazyPagingItems()

    LazyColumn {
        items(count = posts.itemCount) { index ->
            val post = posts[index]
            post?.let {
                PostItem(post)
            }
        }
    }
}

@Composable
fun PostItem(post: Post) {
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(text = post.title, style = androidx.compose.material3.MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(4.dp))
        //Text(text = "Created: ${post.createTime}")
    }
}