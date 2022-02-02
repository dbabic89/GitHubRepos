package com.dariobabic.githubrepos.features.user.data.remote.data_sources

import com.dariobabic.githubrepos.features.user.data.remote.services.UserService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val userService: UserService,
) : RemoteDataSourceContract {

    override fun getUserInfo(accessToken: String) = userService.getUserInfo(accessToken)

    override fun getUserRepos(userName: String) = userService.getUserRepos(userName)
}