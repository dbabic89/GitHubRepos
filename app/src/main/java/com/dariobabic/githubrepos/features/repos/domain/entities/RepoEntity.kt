package com.dariobabic.githubrepos.features.repos.domain.entities

data class RepoEntity(
    val name: String,
    val language: String,
    val description: String?,
    val createdAt: String,
    val updatedAt: String,
    val ownerName: String,
    val ownerAvatarUrl: String,
    val watcherCount: Int,
    val forkCount: Int,
    val issueCount: Int
)