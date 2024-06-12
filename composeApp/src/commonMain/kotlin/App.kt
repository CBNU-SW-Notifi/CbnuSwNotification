
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import database.PeopleDao
import database.Person
import database.entity.InformationEntity
import di.appModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin
import presentation.screen.home.MainScreen

val lightRedColor = Color(color = 0xFFF57D88)
val darkRedColor = Color(color = 0xFF77000B)

@Composable
@Preview
fun App(peopleDao: PeopleDao) {
    initializeKoin()

    val lightColors = lightColorScheme(
        primary = lightRedColor,
        onPrimary = darkRedColor,
        primaryContainer = lightRedColor,
        onPrimaryContainer = darkRedColor
    )
    val darkColors = darkColorScheme(
        primary = lightRedColor,
        onPrimary = darkRedColor,
        primaryContainer = lightRedColor,
        onPrimaryContainer = darkRedColor
    )
    val colors by mutableStateOf(
        if (isSystemInDarkTheme()) darkColors else lightColors
    )

    MaterialTheme(colorScheme = colors) {
        val people by peopleDao.getAllPeople().collectAsState(initial = emptyList())
        val scope = rememberCoroutineScope()

        LaunchedEffect(true) {
            val peopleList = listOf(
                Person(name = "John"),
                Person(name = "Alice"),
                Person(name = "Philipp"),
            )
            peopleList.forEach {
                peopleDao.upsert(it)
            }
        }

        LaunchedEffect(true) {
            val peopleList = listOf(
                InformationEntity(1, "sdffds", "fsdfsd", "fsdfsd", "gdffgd"),
                InformationEntity(2, "sdffds", "fsdfsd", "fsdfsd", "gdffgd"),
                InformationEntity(3, "sdffds", "fsdfsd", "fsdfsd", "gdffgd"),
            )
            peopleList.forEach {
                //informationDao.upsert(it)
            }
        }

        MainScreen()

        /*LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
        ) {
            items(people) { person ->
                Text(
                    text = person.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            scope.launch {
                                peopleDao.delete(person)
                            }
                        }
                        .padding(16.dp)
                )
            }
        }*/
    }
}
fun initializeKoin() {
    startKoin {
        modules(appModule())
    }
}
