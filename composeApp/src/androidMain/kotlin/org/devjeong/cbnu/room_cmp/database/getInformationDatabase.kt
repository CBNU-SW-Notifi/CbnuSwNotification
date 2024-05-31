package org.devjeong.cbnu.room_cmp.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.database.InformationDatabase

fun getInformationDatabase(context: Context): InformationDatabase {
    val dbFile = context.getDatabasePath("information.db")
    return Room.databaseBuilder<InformationDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}