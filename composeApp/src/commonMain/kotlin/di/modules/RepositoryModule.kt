package di.modules

import data.repositories.JobHuntRepository
import org.koin.dsl.module

val provideRepositoryModule = module {
    single { JobHuntRepository(get(), get()) }
}