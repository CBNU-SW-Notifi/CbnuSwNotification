package data.di

import org.koin.dsl.module
import presentation.screen.home.HomeViewModel

val provideviewModelModule = module {
    single {
        HomeViewModel(get())
    }
}