package com.dariobabic.githubrepos.features.owner.domain.use_cases

import com.dariobabic.githubrepos.core.constants.EMPTY_STRING
import com.dariobabic.githubrepos.core.components.use_cases.CompletableUseCase
import com.dariobabic.githubrepos.features.owner.domain.abstractions.OwnerRepositoryContract
import javax.inject.Inject

class GetOwnerDetailsUseCase @Inject constructor(
    private val repository: OwnerRepositoryContract
) : CompletableUseCase() {
    private var ownerName = EMPTY_STRING

    fun setup(ownerName: String): GetOwnerDetailsUseCase {
        this.ownerName = ownerName
        return this
    }

    override fun buildUseCaseCompletable() = repository.getOwnerDetails(ownerName)
}
