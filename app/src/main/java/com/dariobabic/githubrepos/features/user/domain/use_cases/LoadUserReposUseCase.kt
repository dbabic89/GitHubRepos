package com.dariobabic.githubrepos.features.user.domain.use_cases

import com.dariobabic.githubrepos.core.components.use_cases.ObservableUseCase
import com.dariobabic.githubrepos.features.user.domain.abstractions.UserRepositoryContract
import com.dariobabic.githubrepos.features.user.domain.entities.UserRepoEntity
import javax.inject.Inject

class LoadUserReposUseCase @Inject constructor(
    private val repository: UserRepositoryContract
) : ObservableUseCase<List<UserRepoEntity>>() {

    override fun buildUseCaseObservable() = repository.loadUserRepos()
}