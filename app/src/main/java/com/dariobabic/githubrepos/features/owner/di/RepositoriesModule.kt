package com.dariobabic.githubrepos.features.owner.di

import com.dariobabic.githubrepos.features.user.data.local.data_sources.LocalDataSourceContract
import com.dariobabic.githubrepos.features.user.data.remote.data_sources.RemoteDataSourceContract
import com.dariobabic.githubrepos.features.user.data.repositories.UserRepository
import com.dariobabic.githubrepos.features.user.domain.abstractions.UserRepositoryContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    fun providesUserRepository(
        remoteDataSource: RemoteDataSourceContract,
        localDataSource: LocalDataSourceContract
    ): UserRepositoryContract = UserRepository(localDataSource, remoteDataSource)
}