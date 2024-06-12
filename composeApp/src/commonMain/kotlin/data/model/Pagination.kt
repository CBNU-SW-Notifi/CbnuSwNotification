package data.model

import kotlinx.serialization.Serializable

@Serializable
data class Pagination(
    val elementSizeOfPage: Int,
    val currentPage: Int,
    val totalPage: Int,
    val totalElement: Int
)
