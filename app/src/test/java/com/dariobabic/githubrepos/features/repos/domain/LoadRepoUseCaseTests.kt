package com.dariobabic.githubrepos.features.repos.domain

import com.dariobabic.githubrepos.features.repos.domain.abstractions.ReposRepositoryContract
import com.dariobabic.githubrepos.features.repos.domain.use_cases.LoadRepoUseCase
import com.dariobabic.githubrepos.features.repos.fakeEntity1
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class LoadRepoUseCaseTests {

    @MockK
    lateinit var repository: ReposRepositoryContract

    private lateinit var useCase: LoadRepoUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = LoadRepoUseCase(repository)

        every { repository.loadRepo(any()) } returns Single.just(fakeEntity1)
    }

    @Test
    fun useCase_execute_returnsCompleted() {
        // GIVEN
        val name = "GitHubRepos"
        val entity = fakeEntity1

        useCase
            .setup(name)
            // WHEN
            .buildUseCaseSingle()
            .test()
            // THEN
            .assertValue(entity)
            .assertComplete()
    }

    @Test
    fun useCase_execute_verifyFunctionCalls() {
        // GIVEN
        val name = "GitHubRepos"

        useCase
            .setup(name)
            // WHEN
            .buildUseCaseSingle()
            .test()
        // THEN
        verify(exactly = 1) { repository.loadRepo(any()) }
    }
}