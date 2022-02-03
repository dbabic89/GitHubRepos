package com.dariobabic.githubrepos.features.repos.data.remote.services

import com.dariobabic.githubrepos.features.repos.data.remote.models.SearchReposResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ReposService {

    @GET("search/repositories")
    fun getSearchedRepos(
        @Query("q")
        query: String
    ): Observable<SearchReposResponse>
}