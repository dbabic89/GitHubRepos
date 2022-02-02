package com.dariobabic.githubrepos.features.user.data.remote.services

import com.dariobabic.githubrepos.features.repos.data.remote.models.RepoResponse
import com.dariobabic.githubrepos.features.user.data.remote.models.UserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface UserService {

    @Headers("Accept: application/json")
    @GET("user")
    fun getUserInfo(
        @Header("Authorization")
        token: String
    ): Single<UserResponse>

    @GET("/users/{userName}/repos")
    fun getUserRepos(
        @Path("userName")
        userName: String
    ): Single<List<RepoResponse>>
}