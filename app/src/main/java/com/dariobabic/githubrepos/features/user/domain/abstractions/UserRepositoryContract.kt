package com.dariobabic.githubrepos.features.user.domain.abstractions

import com.dariobabic.githubrepos.features.user.domain.entities.UserEntity
import com.dariobabic.githubrepos.features.user.domain.entities.UserRepoEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface UserRepositoryContract {

    fun getUserDetails(accessToken: String): Completable

    fun loadUserInfo(): Observable<List<UserEntity>>

    fun loadUserRepos(): Observable<List<UserRepoEntity>>
}