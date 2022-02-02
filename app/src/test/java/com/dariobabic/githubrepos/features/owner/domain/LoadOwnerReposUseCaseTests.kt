package com.dariobabic.githubrepos.features.owner.domain

import com.dariobabic.githubrepos.features.owner.domain.abstractions.OwnerRepositoryContract
import com.dariobabic.githubrepos.features.owner.domain.use_cases.LoadOwnerReposUseCase
import com.dariobabic.githubrepos.features.owner.fakeOwnerRepoEntity
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class LoadOwnerReposUseCaseTests {

    @MockK
    lateinit var repository: OwnerRepositoryContract

    private lateinit var useCase: LoadOwnerReposUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        useCase = LoadOwnerReposUseCase(repository)

        every { repository.loadOwnerRepos() } returns Observable.fromArray(
            listOf(
                fakeOwnerRepoEntity
            )
        )
    }

    @Test
    fun useCase_execute_returnsCompleted() {
        // GIVEN
        val expected = listOf(fakeOwnerRepoEntity)
        useCase
            // WHEN
            .buildUseCaseObservable()
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
            .buildUseCaseObservable()
            .test()
        // THEN
        verify(exactly = 1) { repository.loadOwnerRepos() }
    }
}