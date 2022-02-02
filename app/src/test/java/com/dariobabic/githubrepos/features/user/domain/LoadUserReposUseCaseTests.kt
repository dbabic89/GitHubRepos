package com.dariobabic.githubrepos.features.user.domain

import com.dariobabic.githubrepos.features.user.domain.abstractions.UserRepositoryContract
import com.dariobabic.githubrepos.features.user.domain.use_cases.LoadUserReposUseCase
import com.dariobabic.githubrepos.features.user.fakeUserRepoEntity
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class LoadUserReposUseCaseTests {

    @MockK
    lateinit var repository: UserRepositoryContract

    private lateinit var useCase: LoadUserReposUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        useCase = LoadUserReposUseCase(repository)

        every { repository.loadUserRepos() }returns Observable.fromArray(listOf(fakeUserRepoEntity))
    }

    @Test
    fun useCase_execute_returnsCompleted() {
        // GIVEN
        val expected = listOf(fakeUserRepoEntity)
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
        verify(exactly = 1) { repository.loadUserRepos() }
    }
}