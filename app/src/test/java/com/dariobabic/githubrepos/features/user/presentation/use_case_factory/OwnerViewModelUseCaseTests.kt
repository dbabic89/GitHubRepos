package com.dariobabic.githubrepos.features.user.presentation.use_case_factory

import com.dariobabic.githubrepos.features.owner.domain.use_cases.GetOwnerDetailsUseCase
import com.dariobabic.githubrepos.features.owner.domain.use_cases.LoadOwnerInfoUseCase
import com.dariobabic.githubrepos.features.owner.domain.use_cases.LoadOwnerReposUseCase
import com.dariobabic.githubrepos.features.owner.fakeOwnerEntity
import com.dariobabic.githubrepos.features.owner.fakeOwnerRepoEntity
import com.dariobabic.githubrepos.features.user.fakeRepoModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.mockk.verifyOrder
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class OwnerViewModelUseCaseTests {

    @MockK
    lateinit var getOwnerDetailsUseCase: GetOwnerDetailsUseCase

    @MockK
    lateinit var loadOwnerInfoUseCase: LoadOwnerInfoUseCase

    @MockK
    lateinit var loadOwnerReposUseCase: LoadOwnerReposUseCase

    private lateinit var useCase: OwnerViewModelUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        useCase = OwnerViewModelUseCase(
            getOwnerDetailsUseCase,
            loadOwnerInfoUseCase,
            loadOwnerReposUseCase
        )

        every { getOwnerDetailsUseCase.setup(any()) } returns getOwnerDetailsUseCase
        every { getOwnerDetailsUseCase.buildUseCaseCompletable() } returns Completable.complete()
        every { loadOwnerInfoUseCase.buildUseCaseSingle() } returns Single.just(fakeOwnerEntity)
        every { loadOwnerReposUseCase.buildUseCaseObservable() } returns
                Observable.fromArray(listOf(fakeOwnerRepoEntity))
    }

    @Test
    fun ownerViewModelUseCase_getInfo_returnsCompleted() {
        // GIVEN
        val ownerName = "dbabic89"

        useCase
            // WHEN
            .setup(ownerName)
            .getInfo()
            .test()
            // THEN
            .assertComplete()
    }

    @Test
    fun ownerViewModelUseCase_getInfo_verifyOrder() {
        // GIVEN
        val ownerName = "dbabic89"

        useCase
            // WHEN
            .setup(ownerName)
            .getInfo()
            .test()
        // THEN
        verifyOrder {
            getOwnerDetailsUseCase.setup(any())
            getOwnerDetailsUseCase.buildUseCaseCompletable()
            loadOwnerInfoUseCase.buildUseCaseSingle()
        }
    }

    @Test
    fun ownerViewModelUseCase_getInfo_verifyFunctionCalls() {
        // GIVEN
        val ownerName = "dbabic89"

        useCase
            // WHEN
            .setup(ownerName)
            .getInfo()
            .test()
        // THEN
        verify(exactly = 1) { getOwnerDetailsUseCase.setup(any()) }
        verify(exactly = 1) { getOwnerDetailsUseCase.buildUseCaseCompletable() }
        verify(exactly = 1) { loadOwnerInfoUseCase.buildUseCaseSingle() }
    }

    @Test
    fun ownerViewModelUseCase_getRepos_returnsCompleted() {
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
    fun ownerViewModelUseCase_getRepos_verifyOrder() {
        // GIVEN
        useCase
            // WHEN
            .getRepos()
            .test()
        // THEN
        verify(exactly = 1) { loadOwnerReposUseCase.buildUseCaseObservable() }
    }

    @Test
    fun ownerViewModelUseCase_getRepos_verifyFunctionCalls() {
        // GIVEN
        useCase
            // WHEN
            .getRepos()
            .test()
        // THEN
        verify(exactly = 1) { loadOwnerReposUseCase.buildUseCaseObservable() }
    }
}