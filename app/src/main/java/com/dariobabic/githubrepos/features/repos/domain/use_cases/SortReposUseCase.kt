package com.dariobabic.githubrepos.features.repos.domain.use_cases

import com.dariobabic.githubrepos.core.components.use_cases.ObservableUseCase
import com.dariobabic.githubrepos.core.constants.EMPTY_STRING
import com.dariobabic.githubrepos.core.constants.SORT_BY_FORKS
import com.dariobabic.githubrepos.core.constants.SORT_BY_STARS
import com.dariobabic.githubrepos.core.constants.SORT_BY_UPDATED
import com.dariobabic.githubrepos.features.repos.domain.abstractions.ReposRepositoryContract
import com.dariobabic.githubrepos.features.repos.domain.entities.RepoEntity
import io.reactivex.Observable
import javax.inject.Inject

class SortReposUseCase @Inject constructor(
    private val repository: ReposRepositoryContract
) : ObservableUseCase<List<RepoEntity>>() {

    private var sort = EMPTY_STRING

    fun setup(sort: String): SortReposUseCase {
        this.sort = sort
        return this
    }

    override fun buildUseCaseObservable(): Observable<List<RepoEntity>> {
        val sortObservable = Observable.fromCallable { sort }
        val reposObservable = repository.loadRepos()
        return Observable.combineLatest(
            sortObservable,
            reposObservable,
            { sortBy, repos -> sortRepos(sortBy, repos) }
        )
    }

    private fun sortRepos(
        sortBy: String,
        repos: List<RepoEntity>
    ): List<RepoEntity> {
        if (repos.isEmpty()) return repos
        return when (sortBy) {
            SORT_BY_STARS -> repos.sortedByDescending { it.watcherCount }
            SORT_BY_FORKS -> repos.sortedByDescending { it.forkCount }
            SORT_BY_UPDATED -> repos.sortedByDescending { it.updatedAt }
            else -> repos
        }
    }
}