package com.dariobabic.githubrepos.features.user.presentation.mappers

import com.dariobabic.githubrepos.features.owner.domain.entities.OwnerEntity
import com.dariobabic.githubrepos.features.owner.domain.entities.OwnerRepoEntity
import com.dariobabic.githubrepos.features.user.domain.entities.UserEntity
import com.dariobabic.githubrepos.features.user.domain.entities.UserRepoEntity
import com.dariobabic.githubrepos.features.user.presentation.models.InfoModel
import com.dariobabic.githubrepos.features.user.presentation.models.RepoModel

fun mapUserEntityListToInfoModelList(entities: List<UserEntity>): List<InfoModel> {
    return entities.map { mapUserEntityToInfoModel(it) }
}

fun mapUserEntityToInfoModel(entity: UserEntity): InfoModel {
    return InfoModel(
        avatarUrl = entity.avatarUrl,
        bio = entity.bio,
        blog = entity.blog,
        login = entity.login,
        company = entity.company,
        createdAt = entity.createdAt,
        followers = entity.followers,
        following = entity.following,
        location = entity.location,
        publicReposCount = entity.publicReposCount
    )
}

fun mapOwnerEntityListToInfoModelList(entities: List<OwnerEntity>): List<InfoModel> {
    return entities.map { mapOwnerEntityToInfoModel(it) }
}

fun mapOwnerEntityToInfoModel(entity: OwnerEntity): InfoModel {
    return InfoModel(
        avatarUrl = entity.avatarUrl,
        bio = entity.bio,
        blog = entity.blog,
        login = entity.login,
        company = entity.company,
        createdAt = entity.createdAt,
        followers = entity.followers,
        following = entity.following,
        location = entity.location,
        publicReposCount = entity.publicReposCount
    )
}


fun mapUserRepoEntityListToModelList(models: List<UserRepoEntity>): List<RepoModel> {
    return models.map { mapUserRepoEntityToModel(it) }
}

fun mapUserRepoEntityToModel(entity: UserRepoEntity): RepoModel {
    return RepoModel(
        name = entity.name,
        language = entity.language,
        watcherCount = entity.watcherCount,
        forkCount = entity.forkCount,
        issueCount = entity.issueCount
    )
}

fun mapOwnerRepoEntityListToModelList(models: List<OwnerRepoEntity>): List<RepoModel> {
    return models.map { mapOwnerRepoEntityToModel(it) }
}

fun mapOwnerRepoEntityToModel(entity: OwnerRepoEntity): RepoModel {
    return RepoModel(
        name = entity.name,
        language = entity.language,
        watcherCount = entity.watcherCount,
        forkCount = entity.forkCount,
        issueCount = entity.issueCount
    )
}