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
        imageUrls = ImageUrl(this.imageUrls.joinToString(",")),
        attachedFiles = AttachedFile(
            name = this.attachedFiles.joinToString(",") { it.name },
            url = this.attachedFiles.joinToString(",") { it.url }
        ),
        postType = this.postType,
        createTime = this.createTime
    )
}

fun JobHuntEntity.toDomain(): PostDetail {
    return PostDetail(
        postId = this.postId,
        title = this.title,
        content = this.content,
        imageUrls = this.imageUrls.url.split(","),
        attachedFiles = this.attachedFiles.url.split(",").zip(this.attachedFiles.name.split(",")).map {
            AttachedFile(it.first, it.second)
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
