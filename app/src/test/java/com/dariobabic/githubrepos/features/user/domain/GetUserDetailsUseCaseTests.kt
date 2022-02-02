package com.dariobabic.githubrepos.features.user.domain

import com.dariobabic.githubrepos.features.user.domain.abstractions.UserRepositoryContract
import com.dariobabic.githubrepos.features.user.domain.use_cases.GetUserDetailsUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test

class GetUserDetailsUseCaseTests {

    @MockK
    lateinit var repository: UserRepositoryContract

    private lateinit var useCase: GetUserDetailsUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        useCase = GetUserDetailsUseCase(repository)

        every { repository.getUserDetails(any()) } returns Completable.complete()
    }

    @Test
    fun useCase_execute_returnsCompleted() {
        // GIVEN
        val accessToken = "1234"

        useCase
            // WHEN
            .setup(accessToken)
            .buildUseCaseCompletable()
            .test()
            // THEN
            .assertComplete()
    }

    @Test
    fun useCase_execute_verifyFunctionCalls() {
        // GIVEN
        val accessToken = "1234"

        useCase
            // WHEN
            .setup(accessToken)
            .buildUseCaseCompletable()
            .test()
        // THEN
        verify(exactly = 1) { repository.getUserDetails(any()) }
    }
}