package com.dariobabic.githubrepos.features.user

import com.dariobabic.githubrepos.features.user.data.local.models.UserModel
import com.dariobabic.githubrepos.features.user.data.local.models.UserRepoModel
import com.dariobabic.githubrepos.features.user.data.remote.models.UserResponse
import com.dariobabic.githubrepos.features.user.domain.entities.UserEntity
import com.dariobabic.githubrepos.features.user.domain.entities.UserRepoEntity
import com.dariobabic.githubrepos.features.user.presentation.models.InfoModel
import com.dariobabic.githubrepos.features.user.presentation.models.RepoModel

val fakeUserResponse = UserResponse(
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

val fakeUserModel = UserModel(
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

val fakeUserEntity = UserEntity(
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

val fakeUserRepoModel = UserRepoModel(
    name = "GitHubRepos",
    language = "Kotlin",
    watcherCount = 999,
    forkCount = 99,
    issueCount = 0
)

val fakeUserRepoEntity = UserRepoEntity(
    name = "GitHubRepos",
    language = "Kotlin",
    watcherCount = 999,
    forkCount = 99,
    issueCount = 0
)

val fakeInfoModel = InfoModel(
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

val fakeRepoModel = RepoModel(
    name = "GitHubRepos",
    language = "Kotlin",
    watcherCount = 999,
    forkCount = 99,
    issueCount = 0
)
