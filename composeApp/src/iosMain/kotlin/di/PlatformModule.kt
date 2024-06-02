package di

import database.InformationDatabase
import database.getInformationDatabase
import org.koin.dsl.module

actual val platformModule = module {
    single<InformationDatabase> {
        getInformationDatabase()
    }
}