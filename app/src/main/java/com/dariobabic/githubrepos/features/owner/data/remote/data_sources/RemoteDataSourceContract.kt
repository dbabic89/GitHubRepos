package com.dariobabic.githubrepos.features.owner.data.remote.data_sources

import com.dariobabic.githubrepos.features.owner.data.remote.models.OwnerResponse
import com.dariobabic.githubrepos.features.repos.data.remote.models.RepoResponse
import io.reactivex.Single

interface RemoteDataSourceContract {

    fun getOwnerInfo(ownerName: String): Single<OwnerResponse>

    fun getOwnerRepos(ownerName: String): Single<List<RepoResponse>>
}