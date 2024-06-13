package di.modules

import database.getJobHuntDatabase
import database.job_hunt.JobHuntDatabase
import org.koin.dsl.module

actual val platformModule = module {
    single<JobHuntDatabase> {
        getJobHuntDatabase()
    }
}