package com.dariobabic.githubrepos.features.user.data.local.mappers

import com.dariobabic.githubrepos.core.constants.EMPTY_STRING
import com.dariobabic.githubrepos.features.user.data.local.models.UserModel
import com.dariobabic.githubrepos.features.user.data.local.models.UserRepoModel
import com.dariobabic.githubrepos.features.user.domain.entities.UserEntity
import com.dariobabic.githubrepos.features.user.domain.entities.UserRepoEntity

fun mapUserModelToEntity(models: List<UserModel>): List<UserEntity> {
    return models.map { model ->
        UserEntity(
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
}

fun mapUserRepoModelListToEntityList(models: List<UserRepoModel>): List<UserRepoEntity> {
    return models.map { mapUserRepoModelToEntity(it) }
}

fun mapUserRepoModelToEntity(model: UserRepoModel): UserRepoEntity {
    return UserRepoEntity(
        name = model.name,
        language = model.language,
        watcherCount = model.watcherCount,
        forkCount = model.forkCount,
        issueCount = model.issueCount
    )
}