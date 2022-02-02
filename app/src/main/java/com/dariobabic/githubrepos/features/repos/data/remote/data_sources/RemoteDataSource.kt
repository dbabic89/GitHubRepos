package com.dariobabic.githubrepos.features.repos.data.remote.data_sources

import com.dariobabic.githubrepos.features.repos.data.remote.services.ReposService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val service: ReposService
) : RemoteDataSourceContract {

    override fun getSearchedRepos(query: String, sort: String) =
        service.getSearchedRepos(query, sort)
}