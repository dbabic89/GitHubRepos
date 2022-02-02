package com.dariobabic.githubrepos.features.owner.domain

import com.dariobabic.githubrepos.features.owner.domain.abstractions.OwnerRepositoryContract
import com.dariobabic.githubrepos.features.owner.domain.use_cases.GetOwnerDetailsUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test

class GetOwnerDetailsUseCaseTests {

    @MockK
    lateinit var repository: OwnerRepositoryContract

    private lateinit var useCase: GetOwnerDetailsUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        useCase = GetOwnerDetailsUseCase(repository)

        every { repository.getOwnerDetails(any()) } returns Completable.complete()
    }

    @Test
    fun useCase_execute_returnsCompleted() {
        // GIVEN
        val ownerName = "dbabic89"

        useCase
            // WHEN
            .setup(ownerName)
            .buildUseCaseCompletable()
            .test()
            // THEN
            .assertComplete()
    }

    @Test
    fun useCase_execute_verifyFunctionCalls() {
        // GIVEN
        val ownerName = "dbabic89"

        useCase
            // WHEN
            .setup(ownerName)
            .buildUseCaseCompletable()
            .test()
        // THEN
        verify(exactly = 1) { repository.getOwnerDetails(any()) }
    }
}