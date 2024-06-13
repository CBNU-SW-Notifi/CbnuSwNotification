package database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import database.job_hunt.JobHuntDatabase
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSLog
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask
import database.job_hunt.instantiateImpl

fun getJobHuntDatabase(): JobHuntDatabase {
    val fileManager = NSFileManager.defaultManager()
    val urls = fileManager.URLsForDirectory(NSDocumentDirectory, NSUserDomainMask)
    val documentsDirectory = urls.first() as NSURL
    val dbFile = documentsDirectory.path + "/job_hunt.db"
    NSLog("Database file path: $dbFile")

    if (!fileManager.fileExistsAtPath(dbFile)) {
        NSLog("Database file does not exist. Creating new file.")
        fileManager.createFileAtPath(dbFile, null, null)
    } else {
        NSLog("Database file already exists.")
    }

    return Room.databaseBuilder<JobHuntDatabase>(
        name = dbFile,
        factory = { JobHuntDatabase::class.instantiateImpl() }
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}