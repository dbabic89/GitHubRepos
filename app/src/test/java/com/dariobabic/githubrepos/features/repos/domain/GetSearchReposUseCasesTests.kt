package com.dariobabic.githubrepos.features.repos.domain

import com.dariobabic.githubrepos.features.repos.domain.abstractions.ReposRepositoryContract
import com.dariobabic.githubrepos.features.repos.domain.use_cases.GetSearchReposUseCase
import com.dariobabic.githubrepos.features.repos.fakeEntityListOf2
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class GetSearchReposUseCasesTests {

    @MockK
    lateinit var repository: ReposRepositoryContract

    lateinit var useCase: GetSearchReposUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = GetSearchReposUseCase(repository)

        every { repository.getSearchedRepos(any()) } returns
                Observable.fromArray(fakeEntityListOf2)
    }

    @Test
    fun useCase_execute_returnsCompleted() {
        // GIVEN
        val query = "android"

        useCase
            // WHEN
            .setup(query)
            .buildUseCaseObservable()
            .test()
            // THEN
            .assertComplete()
    }

    @Test
    fun useCase_execute_verifyFunctionCalls() {
        // GIVEN
        val query = "android"

        useCase
            // WHEN
            .setup(query)
            .buildUseCaseObservable()
            .test()
        // THEN
        verify(exactly = 1) { repository.getSearchedRepos(any()) }
    }
}