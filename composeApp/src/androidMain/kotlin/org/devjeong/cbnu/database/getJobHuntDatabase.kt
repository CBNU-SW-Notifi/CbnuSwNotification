package org.devjeong.cbnu.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import database.job_hunt.JobHuntDatabase

fun getJobHuntDatabase(context: Context): JobHuntDatabase {
    val dbFile = context.getDatabasePath("job_hunt.db")
    return Room.databaseBuilder<JobHuntDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}