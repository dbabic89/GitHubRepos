package com.dariobabic.githubrepos.features.repos.data.local.data_sources

import com.dariobabic.githubrepos.features.repos.data.local.models.RepoModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface LocalDataSourceContract {

    fun clearRepos(): Completable

    fun saveRepos(models: List<RepoModel>): Completable

    fun loadRepos(): Observable<List<RepoModel>>

    fun loadRepo(name: String): Single<RepoModel>
}