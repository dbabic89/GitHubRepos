package com.dariobabic.githubrepos.core.auth.data.remote.mappers

import com.dariobabic.githubrepos.core.auth.domain.entities.AccessTokenEntity
import com.dariobabic.githubrepos.core.auth.data.remote.models.AccessTokenResponse

fun mapAccessTokenResponseToEntity(response: AccessTokenResponse): AccessTokenEntity {
    return AccessTokenEntity(response.access_token, response.scope, response.token_type)
}