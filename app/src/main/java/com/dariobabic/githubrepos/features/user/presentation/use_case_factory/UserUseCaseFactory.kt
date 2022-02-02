package com.dariobabic.githubrepos.features.user.presentation.use_case_factory

import javax.inject.Inject

class UserUseCaseFactory @Inject constructor() {

    @Inject
    lateinit var userViewModelUseCase: UserViewModelUseCase

    @Inject
    lateinit var ownerViewModelUseCase: OwnerViewModelUseCase

    fun getUseCase(name: String, isOwner: Boolean): UserUseCaseContract {
        return when (isOwner) {
            false -> userViewModelUseCase
            true -> ownerViewModelUseCase.setup(name)
        }
    }
}