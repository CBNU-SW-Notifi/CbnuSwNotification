package di

import org.koin.dsl.module
import presentation.screen.home.PostViewModel

val provideviewModelModule = module {
    single {
        PostViewModel(get())
    }

}
