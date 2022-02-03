package com.dariobabic.githubrepos.features.repos.domain.use_cases

import com.dariobabic.githubrepos.core.components.use_cases.ObservableUseCase
import com.dariobabic.githubrepos.core.constants.EMPTY_STRING
import com.dariobabic.githubrepos.features.repos.domain.abstractions.ReposRepositoryContract
import com.dariobabic.githubrepos.features.repos.domain.entities.RepoEntity
import javax.inject.Inject

class GetSearchReposUseCase @Inject constructor(
    private val repository: ReposRepositoryContract
) : ObservableUseCase<List<RepoEntity>>() {

    private var query = EMPTY_STRING

    fun setup(query: String): GetSearchReposUseCase {
        this.query = query
        return this
    }

    override fun buildUseCaseObservable() = repository.getSearchedRepos(query)
}