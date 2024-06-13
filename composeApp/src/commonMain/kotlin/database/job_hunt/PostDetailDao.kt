package database.job_hunt

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDetailDao {
    @Query("SELECT * FROM job_hunt_entity WHERE postId = :postId")
    suspend fun getPostDetail(postId: Int): JobHuntEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPostDetail(postDetail: JobHuntEntity)
}