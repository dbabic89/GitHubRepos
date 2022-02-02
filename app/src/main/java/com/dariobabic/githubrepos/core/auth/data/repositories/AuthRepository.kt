package com.dariobabic.githubrepos.core.auth.data.repositories

import com.dariobabic.githubrepos.core.auth.data.remote.data_sources.RemoteDataSourceContract
import com.dariobabic.githubrepos.core.auth.data.remote.mappers.mapAccessTokenResponseToEntity
import com.dariobabic.githubrepos.core.auth.domain.abstractions.AuthRepositoryContract
import com.dariobabic.githubrepos.core.auth.domain.entities.AccessTokenEntity
import io.reactivex.Single
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSourceContract
) : AuthRepositoryContract {

    override fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String
    ): Single<AccessTokenEntity> = remoteDataSource.getAccessToken(clientId, clientSecret, code)
        .map { mapAccessTokenResponseToEntity(it) }
}