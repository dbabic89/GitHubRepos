package com.dariobabic.githubrepos.features.owner.data.local.mappers

import com.dariobabic.githubrepos.core.constants.EMPTY_STRING
import com.dariobabic.githubrepos.features.owner.data.local.models.OwnerModel
import com.dariobabic.githubrepos.features.owner.data.local.models.OwnerRepoModel
import com.dariobabic.githubrepos.features.owner.domain.entities.OwnerEntity
import com.dariobabic.githubrepos.features.owner.domain.entities.OwnerRepoEntity

fun mapOwnerModelToEntity(model: OwnerModel): OwnerEntity {
    return OwnerEntity(
        avatarUrl = model.avatarUrl,
        bio = model.bio ?: EMPTY_STRING,
        blog = model.blog ?: EMPTY_STRING,
        login = model.login,
        company = model.company ?: EMPTY_STRING,
        createdAt = model.createdAt,
        followers = model.followers,
        following = model.following,
        location = model.location ?: EMPTY_STRING,
        publicReposCount = model.publicReposCount,
    )
}

fun mapOwnerRepoModelListToEntityList(models: List<OwnerRepoModel>): List<OwnerRepoEntity> {
    return models.map { mapOwnerRepoModelToEntity(it) }
}

fun mapOwnerRepoModelToEntity(model: OwnerRepoModel): OwnerRepoEntity {
    return OwnerRepoEntity(
        name = model.name,
        language = model.language,
        watcherCount = model.watcherCount,
        forkCount = model.forkCount,
        issueCount = model.issueCount
    )
}