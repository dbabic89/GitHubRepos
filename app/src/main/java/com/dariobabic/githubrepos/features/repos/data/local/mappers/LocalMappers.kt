package com.dariobabic.githubrepos.features.repos.data.local.mappers

import com.dariobabic.githubrepos.features.repos.data.local.models.RepoModel
import com.dariobabic.githubrepos.features.repos.domain.entities.RepoEntity

fun mapRepoModelListToEntityList(models: List<RepoModel>): List<RepoEntity> {
    return models.map { mapRepoModelToEntity(it) }
}

fun mapRepoModelToEntity(model: RepoModel): RepoEntity {
    return RepoEntity(
        name = model.name,
        language = model.language,
        description = model.description,
        createdAt = model.createdAt,
        updatedAt = model.updatedAt,
        ownerName = model.ownerName,
        ownerAvatarUrl = model.ownerAvatarUrl,
        watcherCount = model.watcherCount,
        forkCount = model.forkCount,
        issueCount = model.issueCount
    )
}