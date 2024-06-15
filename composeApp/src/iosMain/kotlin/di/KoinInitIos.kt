package di

import org.koin.dsl.module
import platform.IosApplicationComponent

fun initKoinIos(appComponent: IosApplicationComponent) {
    initKoin(
        listOf(module { single { appComponent } })
    )
}