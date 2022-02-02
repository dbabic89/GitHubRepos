package com.dariobabic.githubrepos.features.user.domain.entities

data class UserRepoEntity(
    val name: String,
    val language: String,
    val watcherCount: Int,
    val forkCount: Int,
    val issueCount: Int
)