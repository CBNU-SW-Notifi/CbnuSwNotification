package database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Person::class],
    version = 1
)
abstract class PeopleDatabase: RoomDatabase(), DB {

    abstract fun peopleDao(): PeopleDao

    override fun clearAllTables() {
        super.clearAllTables()
    }
}

interface DB {
    fun clearAllTables() {}
}