package com.dariobabic.githubrepos.core.auth.domain.use_cases

import com.dariobabic.githubrepos.core.constants.EMPTY_STRING
import com.dariobabic.githubrepos.core.components.use_cases.SingleUseCase
import com.dariobabic.githubrepos.core.auth.domain.abstractions.AuthRepositoryContract
import com.dariobabic.githubrepos.core.auth.domain.entities.AccessTokenEntity
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val repository: AuthRepositoryContract
) : SingleUseCase<AccessTokenEntity>() {

    private var clientId = EMPTY_STRING
    private var clientSecret = EMPTY_STRING
    private var code = EMPTY_STRING

    fun setup(
        clientId: String,
        clientSecret: String,
        code: String
    ): GetAccessTokenUseCase {
        this.clientId = clientId
        this.clientSecret = clientSecret
        this.code = code
        return this
    }

    override fun buildUseCaseSingle() = repository.getAccessToken(clientId, clientSecret, code)
}