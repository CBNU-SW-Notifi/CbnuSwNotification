package di

import database.InformationDatabase
import org.koin.dsl.module

actual val platformModule = module {
   /* single<InformationDatabase> {
        getInformationDatabase()
    }*/
}