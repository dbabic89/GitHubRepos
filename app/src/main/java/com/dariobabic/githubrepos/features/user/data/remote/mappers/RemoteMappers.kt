package com.dariobabic.githubrepos.features.user.data.remote.mappers

import com.dariobabic.githubrepos.features.repos.data.remote.models.RepoResponse
import com.dariobabic.githubrepos.features.user.data.local.models.UserModel
import com.dariobabic.githubrepos.features.user.data.local.models.UserRepoModel
import com.dariobabic.githubrepos.features.user.data.remote.models.UserResponse

fun mapUserResponseToModel(response: UserResponse): UserModel? {
    val avatarUrl = response.avatar_url ?: return null
    val bio = response.bio
    val blog = response.blog
    val login = response.login ?: return null
    val company = response.company
    val createdAt = response.created_at ?: return null
    val followers = response.followers ?: 0
    val following = response.following ?: 0
    val location = response.location
    val publicReposCount = response.public_repos ?: 0

    return UserModel(
        avatarUrl = avatarUrl,
        bio = bio,
        blog = blog,
        login = login,
        company = company,
        createdAt = createdAt,
        followers = followers,
        following = following,
        location = location,
        publicReposCount = publicReposCount
    )
}

fun mapUserReposResponseListToModelList(responses: List<RepoResponse>): List<UserRepoModel> {
    return responses.mapNotNull { mapUserReposResponseToModel(it) }
}

fun mapUserReposResponseToModel(response: RepoResponse): UserRepoModel? {
    val name = response.name ?: return null
    val language = response.language ?: return null
    val watcherCount = response.watchers_count ?: 0
    val forksCount = response.forks_count ?: 0
    val issueCount = response.open_issues_count ?: 0

    return UserRepoModel(
        name = name,
        language = language,
        watcherCount = watcherCount,
        forkCount = forksCount,
        issueCount = issueCount
    )
}
