package data.remote

import org.koin.dsl.module

val provideRepositoryModule = module {
    single<NetworkRepository> { NetworkRepository(get()) }
}