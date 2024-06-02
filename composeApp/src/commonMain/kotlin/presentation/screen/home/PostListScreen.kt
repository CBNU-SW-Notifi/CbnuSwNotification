package presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.Post

@Composable
fun PostListScreen(postViewModel: PostViewModel) {
    val posts by postViewModel.posts.collectAsState()
    val currentPage by postViewModel.currentPage.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(posts) { post ->
                PostItem(post)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            postViewModel.loadPosts(currentPage + 1, 10)
        }) {
            Text("Load More")
        }
    }

    LaunchedEffect(Unit) {
        postViewModel.loadPosts(0, 10)
    }
}

@Composable
fun PostItem(post: Post) {
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(text = post.title, style = androidx.compose.material3.MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Created: ${post.createTime}")
    }
}