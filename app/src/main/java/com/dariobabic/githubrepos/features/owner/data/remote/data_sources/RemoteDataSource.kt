package com.dariobabic.githubrepos.features.owner.data.remote.data_sources

import com.dariobabic.githubrepos.features.owner.data.remote.services.OwnerService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val service: OwnerService
) : RemoteDataSourceContract {

    override fun getOwnerInfo(ownerName: String) = service.getOwnerInfo(ownerName)

    override fun getOwnerRepos(ownerName: String) = service.getOwnerRepos(ownerName)
}