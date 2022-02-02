package com.dariobabic.githubrepos.features.owner.domain.use_cases

import com.dariobabic.githubrepos.core.components.use_cases.SingleUseCase
import com.dariobabic.githubrepos.features.owner.domain.abstractions.OwnerRepositoryContract
import com.dariobabic.githubrepos.features.owner.domain.entities.OwnerEntity
import javax.inject.Inject

class LoadOwnerInfoUseCase @Inject constructor(
    private val repository: OwnerRepositoryContract
) : SingleUseCase<OwnerEntity>() {

    override fun buildUseCaseSingle() = repository.loadOwnerInfo()
}