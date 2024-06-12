package di

import data.repositories.PostRepositoryImpl
import domain.PostRepository
import domain.PostRepositoryProvider
import org.koin.dsl.module

val provideRepositoryModule = module {
    //single<NetworkRepository> { NetworkRepository(get()) }
    single<PostRepository> {PostRepositoryImpl(get())}
    single {PostRepositoryProvider(get())}
}