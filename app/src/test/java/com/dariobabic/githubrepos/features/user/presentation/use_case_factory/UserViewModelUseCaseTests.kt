package com.dariobabic.githubrepos.features.user.presentation.use_case_factory

import com.dariobabic.githubrepos.features.user.domain.use_cases.LoadUserInfoUseCase
import com.dariobabic.githubrepos.features.user.domain.use_cases.LoadUserReposUseCase
import com.dariobabic.githubrepos.features.user.fakeInfoModel
import com.dariobabic.githubrepos.features.user.fakeRepoModel
import com.dariobabic.githubrepos.features.user.fakeUserEntity
import com.dariobabic.githubrepos.features.user.fakeUserRepoEntity
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class UserViewModelUseCaseTests {

    @MockK
    lateinit var loadUserInfoUseCase: LoadUserInfoUseCase

    @MockK
    lateinit var loadUserReposUseCase: LoadUserReposUseCase

    private lateinit var useCase: UserViewModelUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = UserViewModelUseCase(loadUserInfoUseCase, loadUserReposUseCase)

        every { loadUserInfoUseCase.buildUseCaseObservable() } returns
                Observable.fromArray(listOf(fakeUserEntity))
        every { loadUserReposUseCase.buildUseCaseObservable() } returns
                Observable.fromArray(listOf(fakeUserRepoEntity))
    }

    @Test
    fun userViewModelUseCase_getInfo_returnsCompleted() {
        // GIVEN
        val expectedEntity = listOf(fakeInfoModel)
        useCase
            // WHEN
            .getInfo()
            .test()
            // THEN
            .assertValue(expectedEntity)
            .assertComplete()
    }

    @Test
    fun userViewModelUseCase_getInfo_verifyFunctionCalls() {
        // GIVEN
        useCase
            // WHEN
            .getInfo()
            .test()
        // THEN
        verify(exactly = 1) { loadUserInfoUseCase.buildUseCaseObservable() }
    }

    @Test
    fun userViewModelUseCase_getRepos_returnsCompleted() {
        // GIVEN
        val expectedEntity = listOf(fakeRepoModel)
        useCase
            // WHEN
            .getRepos()
            .test()
            // THEN
            .assertValue(expectedEntity)
            .assertComplete()
    }

    @Test
    fun userViewModelUseCase_getRepos_verifyFunctionCalls() {
        // GIVEN
        useCase
            // WHEN
            .getRepos()
            .test()
        // THEN
        verify(exactly = 1) { loadUserReposUseCase.buildUseCaseObservable() }
    }
}