package com.dariobabic.githubrepos.core.auth.di

import com.dariobabic.githubrepos.core.auth.data.remote.data_sources.RemoteDataSourceContract
import com.dariobabic.githubrepos.core.auth.data.repositories.AuthRepository
import com.dariobabic.githubrepos.core.auth.domain.abstractions.AuthRepositoryContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    fun providesRepositoriesRepository(
        remoteDataSource: RemoteDataSourceContract
    ): AuthRepositoryContract = AuthRepository(remoteDataSource)
}