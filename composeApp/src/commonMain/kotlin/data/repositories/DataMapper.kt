package data.repositories

import data.model.Post
import domain.Post as DomainPost

fun Post.toDomainModel(): DomainPost {
    return DomainPost(
        postId = this.postId,
        title = this.title,
        createTime = this.createTime
    )
}