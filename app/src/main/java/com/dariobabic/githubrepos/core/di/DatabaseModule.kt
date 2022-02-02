package com.dariobabic.githubrepos.core.di

import android.app.Application
import androidx.room.Room
import com.dariobabic.githubrepos.core.database.DataBase
import com.dariobabic.githubrepos.core.constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): DataBase {
        return Room.databaseBuilder(application, DataBase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}