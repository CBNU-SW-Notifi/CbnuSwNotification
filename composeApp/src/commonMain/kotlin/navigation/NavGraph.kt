package navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import presentation.screen.detail.PostDetailScreen
import presentation.screen.home.HomeScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "homeScreen") {
        composable("homeScreen") {
            HomeScreen(navController)
        }

        composable("postDetail/{postId}/{tabTitle}") { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")?.toIntOrNull() ?: return@composable
            val tabTitle = backStackEntry.arguments?.getString("tabTitle") ?: return@composable
            PostDetailScreen(postId, tabTitle, navController)
        }
    }
}