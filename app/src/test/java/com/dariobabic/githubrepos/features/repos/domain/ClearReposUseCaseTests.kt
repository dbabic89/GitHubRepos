package com.dariobabic.githubrepos.features.repos.domain

import com.dariobabic.githubrepos.features.repos.domain.abstractions.ReposRepositoryContract
import com.dariobabic.githubrepos.features.repos.domain.use_cases.ClearReposUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test

class ClearReposUseCaseTests {

    @MockK
    lateinit var repository: ReposRepositoryContract

    lateinit var useCase: ClearReposUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = ClearReposUseCase(repository)

        every { repository.clearRepos() } returns Completable.complete()
    }

    @Test
    fun useCase_execute_returnsCompleted() {
        // GIVEN
        useCase
            // WHEN
            .buildUseCaseCompletable()
            .test()
            // THEN
            .assertComplete()
    }

    @Test
    fun useCase_execute_verifyFunctionCalls() {
        // GIVEN
        useCase
            // WHEN
            .buildUseCaseCompletable()
            .test()
        // THEN
        verify(exactly = 1) { repository.clearRepos() }
    }
}