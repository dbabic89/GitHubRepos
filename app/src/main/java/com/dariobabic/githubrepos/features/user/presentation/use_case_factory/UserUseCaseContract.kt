package com.dariobabic.githubrepos.features.user.presentation.use_case_factory

import com.dariobabic.githubrepos.features.user.presentation.models.InfoModel
import com.dariobabic.githubrepos.features.user.presentation.models.RepoModel
import io.reactivex.Observable

interface UserUseCaseContract {

    fun getInfo(): Observable<List<InfoModel>>

    fun getRepos(): Observable<List<RepoModel>>
}