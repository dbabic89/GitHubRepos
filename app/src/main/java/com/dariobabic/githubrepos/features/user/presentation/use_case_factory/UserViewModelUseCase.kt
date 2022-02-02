package com.dariobabic.githubrepos.features.user.presentation.use_case_factory

import com.dariobabic.githubrepos.features.user.domain.use_cases.LoadUserInfoUseCase
import com.dariobabic.githubrepos.features.user.domain.use_cases.LoadUserReposUseCase
import com.dariobabic.githubrepos.features.user.presentation.mappers.mapUserEntityListToInfoModelList
import com.dariobabic.githubrepos.features.user.presentation.mappers.mapUserRepoEntityListToModelList
import com.dariobabic.githubrepos.features.user.presentation.models.InfoModel
import com.dariobabic.githubrepos.features.user.presentation.models.RepoModel
import io.reactivex.Observable
import javax.inject.Inject

class UserViewModelUseCase @Inject constructor(
    private val loadUserInfoUseCase: LoadUserInfoUseCase,
    private val loadUserReposUseCase: LoadUserReposUseCase
) : UserUseCaseContract {

    override fun getInfo(): Observable<List<InfoModel>> {
        return loadUserInfoUseCase.buildUseCaseObservable()
            .map { mapUserEntityListToInfoModelList(it) }
    }

    override fun getRepos(): Observable<List<RepoModel>> {
        return loadUserReposUseCase.buildUseCaseObservable()
            .map { mapUserRepoEntityListToModelList(it) }
    }
}