package di

import database.InformationDatabase
import org.devjeong.cbnu.room_cmp.database.getInformationDatabase
import org.koin.dsl.module

actual val platformModule = module {
    single<InformationDatabase> {
        getInformationDatabase(get())
    }
}