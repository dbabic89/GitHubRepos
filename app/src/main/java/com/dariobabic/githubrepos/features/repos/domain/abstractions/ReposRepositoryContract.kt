package com.dariobabic.githubrepos.features.repos.domain.abstractions

import com.dariobabic.githubrepos.features.repos.domain.entities.RepoEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface ReposRepositoryContract {

    fun clearRepos(): Completable

    fun getSearchedRepos(query: String, sort: String): Observable<List<RepoEntity>>

    fun loadRepo(name: String): Single<RepoEntity>
}