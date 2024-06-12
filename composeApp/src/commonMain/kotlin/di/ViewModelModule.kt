package di

import org.koin.dsl.module
import presentation.screen.home.MainViewModel

val provideViewModelModule = module {
    single {
        MainViewModel(get())
    }
}
