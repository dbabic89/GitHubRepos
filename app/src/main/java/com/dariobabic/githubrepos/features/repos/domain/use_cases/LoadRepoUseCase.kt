package com.dariobabic.githubrepos.features.repos.domain.use_cases

import com.dariobabic.githubrepos.core.components.use_cases.SingleUseCase
import com.dariobabic.githubrepos.core.constants.EMPTY_STRING
import com.dariobabic.githubrepos.features.repos.domain.abstractions.ReposRepositoryContract
import com.dariobabic.githubrepos.features.repos.domain.entities.RepoEntity
import javax.inject.Inject

class LoadRepoUseCase @Inject constructor(
    private val repository: ReposRepositoryContract
) : SingleUseCase<RepoEntity>() {

    private var name = EMPTY_STRING

    fun setup(name: String): LoadRepoUseCase {
        this.name = name
        return this
    }

    override fun buildUseCaseSingle() = repository.loadRepo(name)
}