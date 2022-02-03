package com.dariobabic.githubrepos.features.repos.domain

import com.dariobabic.githubrepos.core.constants.SORT_BY_STARS
import com.dariobabic.githubrepos.core.constants.SORT_BY_UPDATED
import com.dariobabic.githubrepos.features.repos.domain.abstractions.ReposRepositoryContract
import com.dariobabic.githubrepos.features.repos.domain.use_cases.SortReposUseCase
import com.dariobabic.githubrepos.features.repos.fakeEntityListOf2
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class SortReposUseCaseTests {

    @MockK
    lateinit var repository: ReposRepositoryContract

    private lateinit var useCase: SortReposUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        useCase = SortReposUseCase(repository)

        every { repository.loadRepos() } returns Observable.fromArray(fakeEntityListOf2)
    }

    @Test
    fun useCase_sortByWatchers_verifyOrder() {
        // GIVEN
        val expected = fakeEntityListOf2
        val sortBy = SORT_BY_STARS

        // WHEN
        useCase
            .setup(sortBy)
            .buildUseCaseObservable()
            .test()
            // THEN
            .assertValue(expected)
            .assertComplete()
    }

    @Test
    fun useCase_sortByUpdated_verifyOrder() {
        // GIVEN
        val expected = fakeEntityListOf2.reversed()
        val sortBy = SORT_BY_UPDATED

        // WHEN
        useCase
            .setup(sortBy)
            .buildUseCaseObservable()
            .test()
            // THEN
            .assertValue(expected)
            .assertComplete()
    }
}