package com.dariobabic.githubrepos.core.auth.di

import com.dariobabic.githubrepos.core.auth.data.remote.data_sources.RemoteDataSource
import com.dariobabic.githubrepos.core.auth.data.remote.data_sources.RemoteDataSourceContract
import com.dariobabic.githubrepos.core.auth.data.remote.services.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourcesModule {

    @Provides
    fun provideRemoteDataSource(
        authService: AuthService
    ): RemoteDataSourceContract = RemoteDataSource(authService)
}