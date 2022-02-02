package com.dariobabic.githubrepos.core.auth.domain.entities

data class AccessTokenEntity(
    val accessToken: String?,
    val scope: String?,
    val tokenType: String?
)