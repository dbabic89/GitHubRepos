package com.dariobabic.githubrepos.features.repos

import com.dariobabic.githubrepos.features.repos.data.local.models.RepoModel
import com.dariobabic.githubrepos.features.repos.data.remote.models.LicenseResponse
import com.dariobabic.githubrepos.features.repos.data.remote.models.OwnerResponse
import com.dariobabic.githubrepos.features.repos.data.remote.models.RepoResponse
import com.dariobabic.githubrepos.features.repos.data.remote.models.SearchReposResponse
import com.dariobabic.githubrepos.features.repos.domain.entities.RepoEntity

val fakeOwnerResponse = OwnerResponse(
    login = "dbabic89",
    avatar_url = "https://avatars.githubusercontent.com/u/13087441?v=4"
)

val fakeResponse1 = RepoResponse(
    name = "GitHubRepos",
    language = "Kotlin",
    description = "Android task for Ministry Of Programming",
    created_at = "",
    updated_at = "",
    watchers_count = 999,
    forks_count = 999,
    open_issues_count = 0,
    owner = fakeOwnerResponse,
    license = LicenseResponse()
)

val fakeResponse2 = RepoResponse(
    name = "WeatherBabic",
    language = "Kotlin",
    description = null,
    created_at = "",
    updated_at = "",
    watchers_count = 111,
    forks_count = 111,
    open_issues_count = 0,
    owner = fakeOwnerResponse
)

val fakeResponseImportantFieldsNull = RepoResponse(
    name = null,
    language = "Kotlin",
    description = null,
    created_at = "",
    updated_at = "",
    watchers_count = null,
    forks_count = 999,
    open_issues_count = 0,
    owner = fakeOwnerResponse
)

val fakeEmptyList = emptyList<SearchReposResponse>()
val fakeResponseList = listOf(fakeResponse1, fakeResponse2)
val fakeResponseListImportantFieldNull = listOf(fakeResponse1, fakeResponseImportantFieldsNull)

val fakeEmptyResponse = SearchReposResponse(false, emptyList(), 0)
val fakeSearchResponse = SearchReposResponse(false, fakeResponseList, 2)
val fakeSearchResponseImportantFieldNull =
    SearchReposResponse(false, fakeResponseListImportantFieldNull, 2)


val fakeModel1 = RepoModel(
    name = "GitHubRepos",
    language = "Kotlin",
    description = "Android task for Ministry Of Programming",
    createdAt = "",
    updatedAt = "",
    watcherCount = 999,
    forkCount = 999,
    issueCount = 0,
    ownerName = "dbabic89",
    ownerAvatarUrl = "https://avatars.githubusercontent.com/u/13087441?v=4"
)

val fakeModel2 = RepoModel(
    name = "WeatherBabic",
    language = "Kotlin",
    description = null,
    createdAt = "",
    updatedAt = "",
    watcherCount = 111,
    forkCount = 111,
    issueCount = 0,
    ownerName = "dbabic",
    ownerAvatarUrl = "https://avatars.githubusercontent.com/u/13087441?v=4"
)

val fakeEmptyModel = emptyList<RepoModel>()
val fakeModelListOf1 = listOf(fakeModel1)
val fakeModelListOf2 = listOf(fakeModel1, fakeModel2)

val fakeEntity1 = RepoEntity(
    name = "GitHubRepos",
    language = "Kotlin",
    description = "Android task for Ministry Of Programming",
    createdAt = "",
    updatedAt = "",
    watcherCount = 999,
    forkCount = 999,
    issueCount = 0,
    ownerName = "dbabic89",
    ownerAvatarUrl = "https://avatars.githubusercontent.com/u/13087441?v=4"
)

val fakeEntity2 = RepoEntity(
    name = "WeatherBabic",
    language = "Kotlin",
    description = null,
    createdAt = "",
    updatedAt = "",
    watcherCount = 111,
    forkCount = 111,
    issueCount = 0,
    ownerName = "dbabic",
    ownerAvatarUrl = "https://avatars.githubusercontent.com/u/13087441?v=4"
)

val fakeEmptyEntity = emptyList<RepoEntity>()
val fakeEntityListOf2 = listOf(fakeEntity1, fakeEntity2)