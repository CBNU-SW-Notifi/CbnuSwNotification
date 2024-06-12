import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import database.PeopleDao
import database.Person
import database.entity.InformationEntity
import di.appModule
import navigation.NavGraph
import okio.FileSystem
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin
import presentation.screen.home.MainScreen

val lightRedColor = Color(color = 0xFFF57D88)
val darkRedColor = Color(color = 0xFF77000B)

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun App(peopleDao: PeopleDao) {
    initializeKoin()

    setSingletonImageLoaderFactory { context ->
        getAsyncImageLoader(context)
    }

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

        val navController = rememberNavController()
        NavGraph(navController = navController)

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


fun getAsyncImageLoader(context: PlatformContext) =
    ImageLoader.Builder(context).memoryCachePolicy(CachePolicy.ENABLED).memoryCache {
        MemoryCache.Builder().maxSizePercent(context, 0.3).strongReferencesEnabled(true).build()
    }.diskCachePolicy(CachePolicy.ENABLED).networkCachePolicy(CachePolicy.ENABLED).diskCache {
        newDiskCache()
    }.crossfade(true).logger(DebugLogger()).build()

fun newDiskCache(): DiskCache {
    return DiskCache.Builder().directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "image_cache")
        .maxSizeBytes(1024L * 1024 * 1024) // 512MB
        .build()
}
