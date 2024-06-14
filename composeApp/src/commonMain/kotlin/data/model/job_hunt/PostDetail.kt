package data.model.job_hunt

import kotlinx.serialization.Serializable

@Serializable
data class PostDetail(
    val postId: Int,
    val title: String,
    val content: String,
    val imageUrls: List<String>?,
    val attachedFiles: List<AttachedFile>?,
    val postType: String,
    val createTime: String
)

@Serializable
data class AttachedFile(
    val name: String,
    val url: String
)