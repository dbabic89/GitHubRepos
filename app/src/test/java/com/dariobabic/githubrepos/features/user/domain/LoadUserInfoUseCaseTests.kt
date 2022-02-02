package com.dariobabic.githubrepos.features.user.domain

import com.dariobabic.githubrepos.features.user.domain.abstractions.UserRepositoryContract
import com.dariobabic.githubrepos.features.user.domain.use_cases.LoadUserInfoUseCase
import com.dariobabic.githubrepos.features.user.fakeUserEntity
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class LoadUserInfoUseCaseTests {

    @MockK
    lateinit var repository: UserRepositoryContract

    private lateinit var useCase: LoadUserInfoUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        useCase = LoadUserInfoUseCase(repository)

        every { repository.loadUserInfo() } returns Observable.fromArray(listOf(fakeUserEntity))
    }

    @Test
    fun useCase_execute_returnsCompleted() {
        // GIVEN
        val expected = listOf(fakeUserEntity)

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
        verify(exactly = 1) { repository.loadUserInfo() }
    }
}
