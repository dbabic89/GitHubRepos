package com.dariobabic.githubrepos.core.auth.di

import com.dariobabic.githubrepos.core.di.AuthRetrofit
import com.dariobabic.githubrepos.core.auth.data.remote.services.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {

    @Provides
    fun providesAuthService(@AuthRetrofit retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)
}