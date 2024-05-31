package data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Information::class],
    version = 1
)
abstract class InformationDatabase : RoomDatabase() {

    abstract fun informationDao(): InformationDao
}