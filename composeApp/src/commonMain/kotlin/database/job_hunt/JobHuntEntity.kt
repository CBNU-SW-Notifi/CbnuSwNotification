package database.job_hunt

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import data.model.job_hunt.AttachedFile
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "job_hunt_entity")
data class JobHuntEntity(
    @PrimaryKey val postId: Int,
    val title: String,
    val content: String,
    @Embedded(prefix = "image_")
    val imageUrls: ImageUrl?,
    @Embedded(prefix = "attached_")
    val attachedFiles: AttachedFile?,
    val postType: String,
    val createTime: String
)

@Serializable
data class ImageUrl(
    val url: String
)