package com.dariobabic.githubrepos.features.repos.di

import com.dariobabic.githubrepos.core.di.ApiRetrofit
import com.dariobabic.githubrepos.features.repos.data.remote.services.ReposService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {

    @Provides
    fun providesReposService(@ApiRetrofit retrofit: Retrofit): ReposService =
        retrofit.create(ReposService::class.java)
}