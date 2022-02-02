package com.dariobabic.githubrepos.features.owner.data.remote.services

import com.dariobabic.githubrepos.features.owner.data.remote.models.OwnerResponse
import com.dariobabic.githubrepos.features.repos.data.remote.models.RepoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface OwnerService {

    @GET("users/{ownerName}")
    fun getOwnerInfo(
        @Path("ownerName")
        ownerName: String
    ): Single<OwnerResponse>

    @GET("/users/{ownerName}/repos")
    fun getOwnerRepos(
        @Path("ownerName")
        ownerName: String
    ): Single<List<RepoResponse>>
}