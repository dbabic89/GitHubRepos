package com.dariobabic.githubrepos.features.owner.domain

import com.dariobabic.githubrepos.features.owner.domain.abstractions.OwnerRepositoryContract
import com.dariobabic.githubrepos.features.owner.domain.use_cases.LoadOwnerInfoUseCase
import com.dariobabic.githubrepos.features.owner.fakeOwnerEntity
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class LoadOwnerInfoUseCaseTests {

    @MockK
    lateinit var repository: OwnerRepositoryContract

    private lateinit var useCase: LoadOwnerInfoUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        useCase = LoadOwnerInfoUseCase(repository)

        every { repository.loadOwnerInfo() } returns Single.just(fakeOwnerEntity)
    }

    @Test
    fun useCase_execute_returnsCompleted() {
        // GIVEN
        val expected = fakeOwnerEntity

        useCase
            // WHEN
            .buildUseCaseSingle()
            .test()
            // THEN
            .assertValue(expected)
            .assertComplete()
    }

    @Test
    fun useCase_execute_verifyFunctionCalls() {
        // GIVEN
        useCase
            // WHEN
            .buildUseCaseSingle()
            .test()
        // THEN
        verify(exactly = 1) { repository.loadOwnerInfo() }
    }
}
