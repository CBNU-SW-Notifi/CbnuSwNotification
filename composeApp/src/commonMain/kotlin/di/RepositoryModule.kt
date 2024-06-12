package di

import data.repositories.JobHuntRepository
import org.koin.dsl.module

val provideJobHuntRepositoryModule = module {
    single { JobHuntRepository(get()) }
}