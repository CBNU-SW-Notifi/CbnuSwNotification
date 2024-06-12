package data.model

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val postId: Int,
    val title: String,
    val postType: String,
    val createTime: String
)
