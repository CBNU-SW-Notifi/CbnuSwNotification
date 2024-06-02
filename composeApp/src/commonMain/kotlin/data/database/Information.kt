package data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Information (
    @PrimaryKey
    val postId : Int,
    val title : String,
    val content : String,
    //val imageUrls : List<String>,
    //val attachedFiles : List<String>,
    val postType : String,
    val createTime : String
)