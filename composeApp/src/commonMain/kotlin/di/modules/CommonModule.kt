package di.modules

import data.network.NetworkListener
import org.koin.dsl.module

val commonModule = module {
    single { NetworkListener(get()) }
}