package di.modules

import data.network.NetworkHelper
import database.job_hunt.JobHuntDatabase
import org.devjeong.cbnu.database.getJobHuntDatabase
import org.devjeong.cbnu.network.AndroidNetworkHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformModule = module {
    single<JobHuntDatabase> {
        getJobHuntDatabase(get())
    }
    single<NetworkHelper> { AndroidNetworkHelper(androidContext()) }
}