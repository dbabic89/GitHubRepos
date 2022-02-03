package com.dariobabic.githubrepos.features.repos.data.remote.data_sources

import com.dariobabic.githubrepos.features.repos.data.remote.models.SearchReposResponse
import io.reactivex.Observable

interface RemoteDataSourceContract {

    fun getSearchedRepos(query: String): Observable<SearchReposResponse>
}