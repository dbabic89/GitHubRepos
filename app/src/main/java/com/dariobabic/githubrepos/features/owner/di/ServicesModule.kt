package com.dariobabic.githubrepos.features.owner.di

import com.dariobabic.githubrepos.core.di.ApiRetrofit
import com.dariobabic.githubrepos.features.owner.data.remote.services.OwnerService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {

    @Provides
    fun providesUserService(@ApiRetrofit retrofit: Retrofit): OwnerService =
        retrofit.create(OwnerService::class.java)
}