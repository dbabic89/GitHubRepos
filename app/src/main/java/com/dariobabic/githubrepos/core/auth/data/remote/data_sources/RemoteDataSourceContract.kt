package com.dariobabic.githubrepos.core.auth.data.remote.data_sources

import com.dariobabic.githubrepos.core.auth.data.remote.models.AccessTokenResponse
import io.reactivex.Single

interface RemoteDataSourceContract {

    fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String
    ): Single<AccessTokenResponse>
}