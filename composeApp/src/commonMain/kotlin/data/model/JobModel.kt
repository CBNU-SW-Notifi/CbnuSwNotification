package data.model

import kotlinx.serialization.Serializable
@Serializable
data class Post(
    val postId: Int,
    val title: String,
    val createTime: String
)

@Serializable
data class Pagination(
    val elementSizeOfPage: Int,
    val currentPage: Int,
    val totalPage: Int,
    val totalElements: Int
)

@Serializable
data class JobModel(
    val data: List<Post>,
    val pagination: Pagination
)