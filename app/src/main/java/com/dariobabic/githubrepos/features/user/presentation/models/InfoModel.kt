package com.dariobabic.githubrepos.features.user.presentation.models

data class InfoModel(
    val avatarUrl: String,
    val bio: String,
    val blog: String,
    val login: String,
    val company: String,
    val createdAt: String,
    val followers: Int = 0,
    val following: Int = 0,
    val location: String,
    val publicReposCount: Int = 0)
