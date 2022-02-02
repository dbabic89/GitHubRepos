package com.dariobabic.githubrepos.features.user.presentation.use_case_factory

import com.dariobabic.githubrepos.core.constants.EMPTY_STRING
import com.dariobabic.githubrepos.features.owner.domain.use_cases.GetOwnerDetailsUseCase
import com.dariobabic.githubrepos.features.owner.domain.use_cases.LoadOwnerInfoUseCase
import com.dariobabic.githubrepos.features.owner.domain.use_cases.LoadOwnerReposUseCase
import com.dariobabic.githubrepos.features.user.presentation.mappers.mapOwnerEntityListToInfoModelList
import com.dariobabic.githubrepos.features.user.presentation.mappers.mapOwnerRepoEntityListToModelList
import com.dariobabic.githubrepos.features.user.presentation.models.InfoModel
import com.dariobabic.githubrepos.features.user.presentation.models.RepoModel
import io.reactivex.Observable
import javax.inject.Inject

class OwnerViewModelUseCase @Inject constructor(
    private val getOwnerDetailsUseCase: GetOwnerDetailsUseCase,
    private val loadOwnerInfoUseCase: LoadOwnerInfoUseCase,
    private val loadOwnerReposUseCase: LoadOwnerReposUseCase
) : UserUseCaseContract {

    private var ownerName = EMPTY_STRING

    fun setup(ownerName: String): UserUseCaseContract {
        this.ownerName = ownerName
        return this
    }

    override fun getInfo(): Observable<List<InfoModel>> {
        return getOwnerDetailsUseCase.setup(ownerName)
            .buildUseCaseCompletable()
            .andThen(loadOwnerInfoUseCase.buildUseCaseSingle().toObservable()
                .map { mapOwnerEntityListToInfoModelList(listOf(it)) })
    }

    override fun getRepos(): Observable<List<RepoModel>> {
        return loadOwnerReposUseCase.buildUseCaseObservable()
            .map { mapOwnerRepoEntityListToModelList(it) }
    }
}