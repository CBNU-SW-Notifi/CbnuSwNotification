package data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import core.StringListConverter

@Database(
    entities = [Information::class],
    version = 1
)
//@TypeConverters(StringListConverter::class)
abstract class InformationDatabase : RoomDatabase() {

    abstract fun informationDao(): InformationDao
}