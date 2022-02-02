package com.dariobabic.githubrepos.features.owner.domain.entities

data class OwnerRepoEntity(
    val name: String,
    val language: String,
    val watcherCount: Int,
    val forkCount: Int,
    val issueCount: Int
)