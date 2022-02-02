package com.dariobabic.githubrepos.features.owner.data.local.data_sources

import com.dariobabic.githubrepos.features.owner.data.local.models.OwnerModel
import com.dariobabic.githubrepos.features.owner.data.local.models.OwnerRepoModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface LocalDataSourceContract {

    fun loadOwnerInfo(): Single<OwnerModel>

    fun saveOwnerInfo(model: OwnerModel): Completable

    fun loadOwnerRepos(): Observable<List<OwnerRepoModel>>

    fun saveOwnerRepos(models: List<OwnerRepoModel>): Completable

    fun removeOwnerRepos(): Completable
}