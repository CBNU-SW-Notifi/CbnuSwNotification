package presentation.screen.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import app.cash.paging.compose.collectAsLazyPagingItems
import org.koin.compose.getKoin
import presentation.screen.home.component.PostItem

@Composable
fun NoticesScreen(navController: NavController) {
    val viewModel: HomeViewModel = getKoin().get()
    val posts = viewModel.pagingDataFlow.collectAsLazyPagingItems()

    LazyColumn {
    }
}