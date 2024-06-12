package navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import presentation.screen.PostDetailScreen
import presentation.screen.home.MainScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") {
            MainScreen(navController)
        }
        composable("postDetail/{postId}") { backStackEntry ->
            val postId =
                backStackEntry.arguments?.getString("postId")?.toIntOrNull() ?: return@composable
            PostDetailScreen(postId, navController)
        }
    }
}