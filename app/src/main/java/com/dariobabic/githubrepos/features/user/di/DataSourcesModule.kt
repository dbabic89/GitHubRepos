package com.dariobabic.githubrepos.features.user.di

import com.dariobabic.githubrepos.features.user.data.local.data_sources.LocalDataSource
import com.dariobabic.githubrepos.features.user.data.local.data_sources.LocalDataSourceContract
import com.dariobabic.githubrepos.features.user.data.local.daos.UserDao
import com.dariobabic.githubrepos.features.user.data.remote.data_sources.RemoteDataSource
import com.dariobabic.githubrepos.features.user.data.remote.data_sources.RemoteDataSourceContract
import com.dariobabic.githubrepos.features.user.data.remote.services.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourcesModule {

    @Provides
    fun providesLocalDataSource(
        dao: UserDao
    ): LocalDataSourceContract = LocalDataSource(dao)

    @Provides
    fun provideRemoteDataSource(
        service: UserService,
    ): RemoteDataSourceContract =
        RemoteDataSource(service)
}