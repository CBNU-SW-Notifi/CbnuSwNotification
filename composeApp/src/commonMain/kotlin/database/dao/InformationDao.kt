package database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import database.entity.InformationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InformationDao {

    @Query(value = "SELECT * FROM informationEntity WHERE postId = :id")
    fun getInformation(id: Int): Flow<InformationEntity>

    @Upsert
    suspend fun upsert(information: InformationEntity)
}