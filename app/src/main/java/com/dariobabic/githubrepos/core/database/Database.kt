package com.dariobabic.githubrepos.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dariobabic.githubrepos.BuildConfig
import com.dariobabic.githubrepos.features.owner.data.local.daos.OwnerDao
import com.dariobabic.githubrepos.features.owner.data.local.models.OwnerModel
import com.dariobabic.githubrepos.features.owner.data.local.models.OwnerRepoModel
import com.dariobabic.githubrepos.features.repos.data.local.daos.ReposDao
import com.dariobabic.githubrepos.features.repos.data.local.models.RepoModel
import com.dariobabic.githubrepos.features.user.data.local.daos.UserDao
import com.dariobabic.githubrepos.features.user.data.local.models.UserModel
import com.dariobabic.githubrepos.features.user.data.local.models.UserRepoModel

@Database(
    entities = [
        OwnerModel::class,
        OwnerRepoModel::class,
        RepoModel::class,
        UserModel::class,
        UserRepoModel::class
    ],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
abstract class DataBase : RoomDatabase() {

    abstract fun ownerDao(): OwnerDao
    abstract fun reposDao(): ReposDao
    abstract fun userDao(): UserDao
}