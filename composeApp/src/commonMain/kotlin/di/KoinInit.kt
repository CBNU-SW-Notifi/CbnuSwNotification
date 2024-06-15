package di

import di.modules.commonModule
import di.modules.platformModule
import di.modules.provideDatabaseModule
import di.modules.provideHttpClientModule
import di.modules.provideRepositoryModule
import di.modules.provideViewModelModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initKoin(additionalModules: List<Module> = listOf(), appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            additionalModules + listOf(
                platformModule,
                provideViewModelModule,
                provideHttpClientModule,
                provideDatabaseModule,
                provideRepositoryModule,
                commonModule
            )
        )
    }
