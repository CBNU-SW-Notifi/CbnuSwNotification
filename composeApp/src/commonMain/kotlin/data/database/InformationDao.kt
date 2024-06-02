package data.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface InformationDao {

    @Query(value = "SELECT * FROM information WHERE postId = :id")
    fun getInformation(id: Int): Flow<Information>

}