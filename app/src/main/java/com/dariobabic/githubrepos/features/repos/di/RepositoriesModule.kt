package com.dariobabic.githubrepos.features.repos.di

import com.dariobabic.githubrepos.features.repos.data.local.data_sources.LocalDataSourceContract
import com.dariobabic.githubrepos.features.repos.data.remote.data_sources.RemoteDataSourceContract
import com.dariobabic.githubrepos.features.repos.data.repositories.ReposRepository
import com.dariobabic.githubrepos.features.repos.domain.abstractions.ReposRepositoryContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    fun providesRepsRepository(
        remoteDataSource: RemoteDataSourceContract,
        localDataSource: LocalDataSourceContract
    ): ReposRepositoryContract = ReposRepository(localDataSource, remoteDataSource)
}