package data.model.job_hunt

import kotlinx.serialization.Serializable

@Serializable
data class PostDetail(
    val postId: Int,
    val title: String,
    val content: String,
    val imageUrls: List<String>,
    val attachedFiles: List<String>,
    val postType: String,
    val createTime: String
)