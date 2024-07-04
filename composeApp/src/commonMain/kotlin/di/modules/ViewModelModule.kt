package di.modules

import org.koin.dsl.module
import presentation.screen.home.HomeViewModel
import presentation.screen.detail.PostDetailViewModel

val provideViewModelModule = module {
    single {
        HomeViewModel(get(), get())
    }
    single {
        PostDetailViewModel(get())
    }
}
