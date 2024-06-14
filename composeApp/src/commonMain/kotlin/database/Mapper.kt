package database

import data.model.job_hunt.AttachedFile
import data.model.job_hunt.PostDetail
import database.job_hunt.ImageUrl
import database.job_hunt.JobHuntEntity

fun PostDetail.toEntity(): JobHuntEntity {
    return JobHuntEntity(
        postId = this.postId,
        title = this.title,
        content = this.content,
        imageUrls = this.imageUrls?.let { ImageUrl(it.joinToString(",")) },
        attachedFiles = this.attachedFiles?.let {
            AttachedFile(
                name = it.joinToString(",") { file -> file.name },
                url = it.joinToString(",") { file -> file.url }
            )
        },
        postType = this.postType,
        createTime = this.createTime
    )
}

fun JobHuntEntity.toDomain(): PostDetail {
    return PostDetail(
        postId = this.postId,
        title = this.title,
        content = this.content,
        imageUrls = this.imageUrls?.url?.split(","),
        attachedFiles = this.attachedFiles?.let {
            it.url.split(",").zip(it.name.split(",")).map { pair ->
                AttachedFile(pair.second, pair.first)
            }
        },
        postType = this.postType,
        createTime = this.createTime
    )
}

fun List<PostDetail>.toJobHuntEntityList(): List<JobHuntEntity> {
    return this.map { it.toEntity() }
}

fun List<JobHuntEntity>.toPostDetailDomainList(): List<PostDetail> {
    return this.map { it.toDomain() }
}
