package com.dariobabic.githubrepos.features.user.di

import com.dariobabic.githubrepos.core.di.ApiRetrofit
import com.dariobabic.githubrepos.features.user.data.remote.services.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {

    @Provides
    fun providesUserService(@ApiRetrofit retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)
}