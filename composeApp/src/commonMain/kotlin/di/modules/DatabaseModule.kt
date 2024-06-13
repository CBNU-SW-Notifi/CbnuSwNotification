package di.modules

import androidx.room.Room
import database.job_hunt.JobHuntDatabase
import org.koin.dsl.module

val provideDatabaseModule = module {
    single { get<JobHuntDatabase>().postDetailDao() }
}
