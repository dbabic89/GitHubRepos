package com.dariobabic.githubrepos.core.auth.data.remote.data_sources

import com.dariobabic.githubrepos.core.auth.data.remote.models.AccessTokenResponse
import com.dariobabic.githubrepos.core.auth.data.remote.services.AuthService
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val service: AuthService
) : RemoteDataSourceContract {

    override fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String
    ): Single<AccessTokenResponse> = service.getAccessToken(clientId, clientSecret, code)
}