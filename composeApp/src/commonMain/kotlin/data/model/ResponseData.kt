package data.model

import kotlinx.serialization.Serializable

@Serializable
data class ResponseData(
    val data: List<Post>,
    val pagination: Pagination
)