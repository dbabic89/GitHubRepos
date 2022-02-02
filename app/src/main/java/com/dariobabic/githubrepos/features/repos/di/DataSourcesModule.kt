package com.dariobabic.githubrepos.features.repos.di

import com.dariobabic.githubrepos.features.repos.data.local.daos.ReposDao
import com.dariobabic.githubrepos.features.repos.data.local.data_sources.LocalDataSource
import com.dariobabic.githubrepos.features.repos.data.local.data_sources.LocalDataSourceContract
import com.dariobabic.githubrepos.features.repos.data.remote.data_sources.RemoteDataSource
import com.dariobabic.githubrepos.features.repos.data.remote.data_sources.RemoteDataSourceContract
import com.dariobabic.githubrepos.features.repos.data.remote.services.ReposService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourcesModule {

    @Provides
    fun providesLocalDataSource(dao: ReposDao): LocalDataSourceContract =
        LocalDataSource(dao)

    @Provides
    fun provideRemoteDataSource(service: ReposService): RemoteDataSourceContract =
        RemoteDataSource(service)
}