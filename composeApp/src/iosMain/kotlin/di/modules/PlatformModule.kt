package di.modules

import data.network.NetworkHelper
import database.getJobHuntDatabase
import database.job_hunt.JobHuntDatabase
import network.IosApplicationComponent
import org.koin.dsl.module

actual val platformModule = module {
    single<JobHuntDatabase> {
        getJobHuntDatabase()
    }
    single<NetworkHelper> { get<IosApplicationComponent>().networkHelper }
}