package database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory

/*
fun getInformationDatabase(): InformationDatabase {
    val dbFile = NSHomeDirectory() + "/information.db"
    return Room.databaseBuilder<InformationDatabase>(
        name = dbFile,
        factory = { InformationDatabase::class.instantiateImpl() }
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}*/
