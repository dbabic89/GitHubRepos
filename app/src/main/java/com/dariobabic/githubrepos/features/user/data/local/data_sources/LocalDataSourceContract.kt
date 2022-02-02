package com.dariobabic.githubrepos.features.user.data.local.data_sources

import com.dariobabic.githubrepos.features.user.data.local.models.UserModel
import com.dariobabic.githubrepos.features.user.data.local.models.UserRepoModel
import io.reactivex.Completable
import io.reactivex.Observable

interface LocalDataSourceContract {

    fun loadUserInfo(): Observable<List<UserModel>>

    fun saveUserInfo(model: UserModel): Completable

    fun loadUserRepos(): Observable<List<UserRepoModel>>

    fun saveUserRepos(models: List<UserRepoModel>): Completable
}