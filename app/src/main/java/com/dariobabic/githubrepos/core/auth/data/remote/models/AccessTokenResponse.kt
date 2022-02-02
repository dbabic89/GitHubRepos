package com.dariobabic.githubrepos.core.auth.data.remote.models

data class AccessTokenResponse(
    val access_token: String?,
    val scope: String?,
    val token_type: String?
)