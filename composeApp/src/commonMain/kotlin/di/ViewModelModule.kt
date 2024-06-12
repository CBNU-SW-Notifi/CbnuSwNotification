package di

import org.koin.dsl.module
import presentation.screen.home.MainViewModel
import presentation.screen.home.PostDetailViewModel

val provideViewModelModule = module {
    single {
        MainViewModel(get())
    }
    single {
        PostDetailViewModel(get())
    }
}
