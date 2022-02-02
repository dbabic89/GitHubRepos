package com.dariobabic.githubrepos.features.user.data.remote.data_sources

import com.dariobabic.githubrepos.features.repos.data.remote.models.RepoResponse
import com.dariobabic.githubrepos.features.user.data.remote.models.UserResponse
import io.reactivex.Single

interface RemoteDataSourceContract {

    fun getUserInfo(accessToken: String): Single<UserResponse>

    fun getUserRepos(userName: String): Single<List<RepoResponse>>
}