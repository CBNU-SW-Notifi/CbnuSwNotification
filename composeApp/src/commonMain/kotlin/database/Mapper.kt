package database

import data.model.job_hunt.AttachedFile
import data.model.job_hunt.JobHuntDetail
import database.job_hunt.ImageUrl
import database.job_hunt.JobHuntEntity

fun JobHuntDetail.toEntity(): JobHuntEntity {
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
        createTime = this.createTime
    )
}

fun JobHuntEntity.toDomain(): JobHuntDetail {
    return JobHuntDetail(
        postId = this.postId,
        title = this.title,
        content = this.content,
        imageUrls = this.imageUrls?.url?.split(","),
        attachedFiles = this.attachedFiles?.let {
            it.url.split(",").zip(it.name.split(",")).map { pair ->
                AttachedFile(pair.second, pair.first)
            }
        },
        createTime = this.createTime
    )
}

fun List<JobHuntDetail>.toJobHuntEntityList(): List<JobHuntEntity> {
    return this.map { it.toEntity() }
}

fun List<JobHuntEntity>.toPostDetailDomainList(): List<JobHuntDetail> {
    return this.map { it.toDomain() }
}
