package di.modules

import database.job_hunt.JobHuntDatabase
import org.devjeong.cbnu.database.getJobHuntDatabase
import org.koin.dsl.module

actual val platformModule = module {
    single<JobHuntDatabase> {
        getJobHuntDatabase(get())
    }
}