package data.di

import org.koin.dsl.module
import presentation.screen.home.HomeViewModel
import presentation.screen.home.PostViewModel

val provideviewModelModule = module {
    single {
        HomeViewModel(get())
    }
    single {
        PostViewModel(get())
    }

}