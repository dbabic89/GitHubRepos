package com.dariobabic.githubrepos.features.owner

import com.dariobabic.githubrepos.features.owner.data.local.models.OwnerModel
import com.dariobabic.githubrepos.features.owner.data.local.models.OwnerRepoModel
import com.dariobabic.githubrepos.features.owner.data.remote.models.OwnerResponse
import com.dariobabic.githubrepos.features.owner.domain.entities.OwnerEntity
import com.dariobabic.githubrepos.features.owner.domain.entities.OwnerRepoEntity

val fakeOwnerResponse = OwnerResponse(
    avatar_url = "https://avatars.githubusercontent.com/u/13087441?v=4",
    bio = "In west Philadelphia born and raised On the playground was where I spent most of my days",
    blog = "medium/dbabic89",
    login = "dbabic89",
    company = "Virgin Pulse",
    created_at = "2012-01-28T17:29:58Z",
    followers = 999,
    following = 1,
    location = "Zepce",
    public_repos = 99
)

val fakeOwnerModel = OwnerModel(
    avatarUrl = "https://avatars.githubusercontent.com/u/13087441?v=4",
    bio = "In west Philadelphia born and raised On the playground was where I spent most of my days",
    blog = "medium/dbabic89",
    login = "dbabic89",
    company = "Virgin Pulse",
    createdAt = "2012-01-28T17:29:58Z",
    followers = 999,
    following = 1,
    location = "Zepce",
    publicReposCount = 99
)

val fakeOwnerEntity = OwnerEntity(
    avatarUrl = "https://avatars.githubusercontent.com/u/13087441?v=4",
    bio = "In west Philadelphia born and raised On the playground was where I spent most of my days",
    blog = "medium/dbabic89",
    login = "dbabic89",
    company = "Virgin Pulse",
    createdAt = "2012-01-28T17:29:58Z",
    followers = 999,
    following = 1,
    location = "Zepce",
    publicReposCount = 99
)

val fakeOwnerRepoModel = OwnerRepoModel(
    name = "GitHubRepos",
    language = "Kotlin",
    watcherCount = 999,
    forkCount = 99,
    issueCount = 0
)

val fakeOwnerRepoEntity = OwnerRepoEntity(
    name = "GitHubRepos",
    language = "Kotlin",
    watcherCount = 999,
    forkCount = 99,
    issueCount = 0
)