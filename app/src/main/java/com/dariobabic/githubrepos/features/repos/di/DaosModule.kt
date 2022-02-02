package com.dariobabic.githubrepos.features.repos.di

import com.dariobabic.githubrepos.core.database.DataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesReposDao(dataBase: DataBase) = dataBase.reposDao()
}