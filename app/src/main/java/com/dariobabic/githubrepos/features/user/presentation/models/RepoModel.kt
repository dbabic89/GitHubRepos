package com.dariobabic.githubrepos.features.user.presentation.models

data class RepoModel(
    val name: String,
    val language: String,
    val watcherCount: Int,
    val forkCount: Int,
    val issueCount: Int
)
