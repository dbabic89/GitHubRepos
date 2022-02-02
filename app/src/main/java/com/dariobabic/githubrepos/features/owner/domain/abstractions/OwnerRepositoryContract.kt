package com.dariobabic.githubrepos.features.owner.domain.abstractions

import com.dariobabic.githubrepos.features.owner.domain.entities.OwnerEntity
import com.dariobabic.githubrepos.features.owner.domain.entities.OwnerRepoEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface OwnerRepositoryContract {

    fun getOwnerDetails(ownerName: String): Completable

    fun loadOwnerInfo(): Single<OwnerEntity>

    fun loadOwnerRepos(): Observable<List<OwnerRepoEntity>>
}