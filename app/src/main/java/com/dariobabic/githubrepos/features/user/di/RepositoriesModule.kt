package com.dariobabic.githubrepos.features.user.di

import com.dariobabic.githubrepos.features.owner.data.local.data_sources.LocalDataSourceContract
import com.dariobabic.githubrepos.features.owner.data.remote.data_sources.RemoteDataSourceContract
import com.dariobabic.githubrepos.features.owner.data.repositories.OwnerRepository
import com.dariobabic.githubrepos.features.owner.domain.abstractions.OwnerRepositoryContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    fun providesOwnerRepository(
        remoteDataSource: RemoteDataSourceContract,
        localDataSource: LocalDataSourceContract
    ): OwnerRepositoryContract = OwnerRepository(localDataSource, remoteDataSource)
}