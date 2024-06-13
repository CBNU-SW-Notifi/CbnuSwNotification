package database.job_hunt

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import database.util.Converters

@Database(entities = [JobHuntEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class JobHuntDatabase : RoomDatabase(), DB {
    abstract fun postDetailDao(): PostDetailDao

    override fun clearAllTables() {
        super.clearAllTables()
    }
}

interface DB {
    fun clearAllTables() {}
}