package database

import androidx.room.Database
import androidx.room.RoomDatabase
import database.dao.InformationDao
import database.entity.InformationEntity

@Database(
    entities = [InformationEntity::class],
    version = 1
)
//@TypeConverters(StringListConverter::class)
abstract class InformationDatabase : RoomDatabase(), DB {

    abstract fun informationDao(): InformationDao

    override fun clearAllTables() {
        super.clearAllTables()
    }
}

// Class 'AppDatabase_Impl' is not abstract and does not implement abstract base class member 'clearAllTables'.
/*
interface DB {
    fun clearAllTables() {}
}*/
