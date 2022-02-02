package com.dariobabic.githubrepos.core.auth.domain.abstractions

import com.dariobabic.githubrepos.core.auth.domain.entities.AccessTokenEntity
import io.reactivex.Single

interface AuthRepositoryContract {

    fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String
    ): Single<AccessTokenEntity>
}