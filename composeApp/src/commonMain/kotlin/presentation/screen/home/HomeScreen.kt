package presentation.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val tabs = listOf("취업 정보", "학과 공지")
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val coroutineScope = rememberCoroutineScope()

    val selectedColor = Color(0xFFBC2055)
    val unselectedColor = Color.Black

    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = @Composable { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    color = selectedColor
                )
            },
            divider = {
                HorizontalDivider(color = Color(0xFFDEDEDE), thickness = 1.dp)
            }
        ) {
            tabs.forEachIndexed { index, title ->
                val selected = pagerState.currentPage == index
                val interactionSource = remember { MutableInteractionSource() }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .background(Color.White)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = rememberRipple(color = selectedColor),
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.scrollToPage(index)
                                }
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        color = if (selected) selectedColor else unselectedColor,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                    )
                }
            }
        }

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false
        ) { page ->
            when (tabs[page]) {
                "취업 정보" -> JobInfoScreen(navController)
                "학과 공지" -> NoticesScreen(navController)
            }
        }
    }
}