package data.di

import data.repositories.JobRepositoryImpl
import domain.JobRepository
import org.koin.dsl.module

val provideRepositoryModule = module {
    //single<NetworkRepository> { NetworkRepository(get()) }
    single<JobRepository> {JobRepositoryImpl(get())}
}