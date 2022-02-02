package com.dariobabic.githubrepos.features.owner.domain.use_cases

import com.dariobabic.githubrepos.core.components.use_cases.ObservableUseCase
import com.dariobabic.githubrepos.features.owner.domain.abstractions.OwnerRepositoryContract
import com.dariobabic.githubrepos.features.owner.domain.entities.OwnerRepoEntity
import javax.inject.Inject

class LoadOwnerReposUseCase @Inject constructor(
    private val repository: OwnerRepositoryContract
) : ObservableUseCase<List<OwnerRepoEntity>>() {

    override fun buildUseCaseObservable() = repository.loadOwnerRepos()
}