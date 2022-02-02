package com.dariobabic.githubrepos.features.repos.data.remote.mappers

import com.dariobabic.githubrepos.features.repos.data.local.models.RepoModel
import com.dariobabic.githubrepos.features.repos.data.remote.models.RepoResponse
import com.dariobabic.githubrepos.features.repos.data.remote.models.SearchReposResponse

fun mapSearchReposResponseToModel(response: SearchReposResponse): List<RepoModel> {
    return response.items?.filterNotNull()?.let { mapReposResponseListToModel(it) } ?: emptyList()
}

fun mapReposResponseListToModel(responses: List<RepoResponse>): List<RepoModel> {
    return responses.mapNotNull { mapReposResponseToModel(it) }
}

fun mapReposResponseToModel(response: RepoResponse): RepoModel? {
    val name = response.name ?: return null
    val language = response.language ?: return null
    val description = response.description
    val createdAt = response.created_at ?: return null
    val updatedAt = response.updated_at ?: return null
    val watcherCount = response.watchers_count ?: 0
    val forksCount = response.forks_count ?: 0
    val issueCount = response.open_issues_count ?: 0

    val owner = response.owner ?: return null
    val ownerName = owner.login ?: return null
    val ownerAvatarUrl = owner.avatar_url ?: return null

    return RepoModel(
        name = name,
        language = language,
        description = description,
        createdAt = createdAt,
        updatedAt = updatedAt,
        ownerName = ownerName,
        ownerAvatarUrl = ownerAvatarUrl,
        watcherCount = watcherCount,
        forkCount = forksCount,
        issueCount = issueCount
    )
}