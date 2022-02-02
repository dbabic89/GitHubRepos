package com.dariobabic.githubrepos.features.owner.di

import com.dariobabic.githubrepos.features.owner.data.local.daos.OwnerDao
import com.dariobabic.githubrepos.features.owner.data.local.data_sources.LocalDataSource
import com.dariobabic.githubrepos.features.owner.data.local.data_sources.LocalDataSourceContract
import com.dariobabic.githubrepos.features.owner.data.remote.data_sources.RemoteDataSource
import com.dariobabic.githubrepos.features.owner.data.remote.data_sources.RemoteDataSourceContract
import com.dariobabic.githubrepos.features.owner.data.remote.services.OwnerService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourcesModule {

    @Provides
    fun providesLocalDataSource(
        dao: OwnerDao
    ): LocalDataSourceContract = LocalDataSource(dao)

    @Provides
    fun provideRemoteDataSource(
        service: OwnerService
    ): RemoteDataSourceContract =
        RemoteDataSource(service)
}