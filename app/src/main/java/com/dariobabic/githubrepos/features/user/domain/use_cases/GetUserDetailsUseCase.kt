package com.dariobabic.githubrepos.features.user.domain.use_cases

import com.dariobabic.githubrepos.core.constants.EMPTY_STRING
import com.dariobabic.githubrepos.core.components.use_cases.CompletableUseCase
import com.dariobabic.githubrepos.features.user.domain.abstractions.UserRepositoryContract
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val repository: UserRepositoryContract
) : CompletableUseCase() {

    private var accessToken = EMPTY_STRING

    fun setup(accessToken: String?): CompletableUseCase {
        this.accessToken = accessToken ?: EMPTY_STRING
        return this
    }

    override fun buildUseCaseCompletable() = repository.getUserDetails(accessToken)
}