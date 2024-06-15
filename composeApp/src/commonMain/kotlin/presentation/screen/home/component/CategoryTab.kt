package presentation.screen.home.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import presentation.screen.home.JobInfoScreen
import presentation.screen.home.NoticesScreen

@Composable
fun CategoryTab(tabTitle: String, navController: NavController) {
    when (tabTitle) {
        "학과 공지" -> NoticesScreen(navController)
        "취업 정보" -> JobInfoScreen(navController)
    }
}