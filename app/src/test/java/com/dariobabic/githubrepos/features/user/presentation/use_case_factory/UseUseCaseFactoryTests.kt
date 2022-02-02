package com.dariobabic.githubrepos.features.user.presentation.use_case_factory

import com.dariobabic.githubrepos.core.constants.EMPTY_STRING
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UseUseCaseFactoryTests {

    @MockK
    lateinit var userViewModelUseCase: UserViewModelUseCase

    @MockK
    lateinit var ownerViewModelUseCase: OwnerViewModelUseCase

    private lateinit var userUseCaseFactory: UserUseCaseFactory

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        userUseCaseFactory = UserUseCaseFactory()
        UserUseCaseFactory_MembersInjector
            .injectUserViewModelUseCase(userUseCaseFactory, userViewModelUseCase)
        UserUseCaseFactory_MembersInjector
            .injectOwnerViewModelUseCase(userUseCaseFactory, ownerViewModelUseCase)

        every { ownerViewModelUseCase.setup(any()) } returns ownerViewModelUseCase
    }

    @Test
    fun userUseCaseFactory_passingIsOwnerTrue_returningOwnerViewModelUseCase() {
        // GIVEN
        val isOwner = true
        val name = "dbabic89"
        // WHEN
        val useCase = userUseCaseFactory.getUseCase(name, isOwner)
        // THEN
        Assert.assertSame(ownerViewModelUseCase, useCase)
    }

    @Test
    fun userUseCaseFactory_passingIsOwnerFalse_returningOwnerViewModelUseCase() {
        // GIVEN
        val isOwner = false
        val name = EMPTY_STRING
        // WHEN
        val useCase = userUseCaseFactory.getUseCase(name, isOwner)
        // THEN
        Assert.assertSame(userViewModelUseCase, useCase)
    }
}