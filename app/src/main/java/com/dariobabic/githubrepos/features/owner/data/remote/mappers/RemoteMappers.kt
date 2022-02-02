package com.dariobabic.githubrepos.features.owner.data.remote.mappers

import com.dariobabic.githubrepos.features.owner.data.local.models.OwnerModel
import com.dariobabic.githubrepos.features.owner.data.local.models.OwnerRepoModel
import com.dariobabic.githubrepos.features.owner.data.remote.models.OwnerResponse
import com.dariobabic.githubrepos.features.repos.data.remote.models.RepoResponse

fun mapOwnerResponseToModel(response: OwnerResponse): OwnerModel? {
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

    return OwnerModel(
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

fun mapOwnerReposResponseListToModelList(responses: List<RepoResponse>): List<OwnerRepoModel> {
    return responses.mapNotNull { mapOwnerReposResponseToModel(it) }
}

fun mapOwnerReposResponseToModel(response: RepoResponse): OwnerRepoModel? {
    val name = response.name ?: return null
    val language = response.language ?: return null
    val watcherCount = response.watchers_count ?: 0
    val forksCount = response.forks_count ?: 0
    val issueCount = response.open_issues_count ?: 0

    return OwnerRepoModel(
        name = name,
        language = language,
        watcherCount = watcherCount,
        forkCount = forksCount,
        issueCount = issueCount
    )
}
