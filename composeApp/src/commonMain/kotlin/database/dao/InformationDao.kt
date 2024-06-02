package database.dao

import androidx.room.Dao
import androidx.room.Query
import database.entity.InformationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InformationDao {

    @Query(value = "SELECT * FROM informationEntity WHERE postId = :id")
    fun getInformation(id: Int): Flow<InformationEntity>

}