package di

import di.modules.platformModule
import di.modules.provideDatabaseModule
import di.modules.provideHttpClientModule
import di.modules.provideRepositoryModule
import di.modules.provideViewModelModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            platformModule,
            provideViewModelModule,
            provideHttpClientModule,
            provideDatabaseModule,
            provideRepositoryModule
        )
    }

//using in iOS
fun initKoin() = initKoin {}