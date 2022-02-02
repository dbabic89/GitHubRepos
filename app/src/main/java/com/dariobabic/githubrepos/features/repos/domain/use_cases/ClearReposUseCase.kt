package com.dariobabic.githubrepos.features.repos.domain.use_cases

import com.dariobabic.githubrepos.core.components.use_cases.CompletableUseCase
import com.dariobabic.githubrepos.features.repos.domain.abstractions.ReposRepositoryContract
import javax.inject.Inject

class ClearReposUseCase @Inject constructor(
    private val repository: ReposRepositoryContract
) : CompletableUseCase() {

    override fun buildUseCaseCompletable() = repository.clearRepos()
}