package com.dariobabic.githubrepos.features.user.presentation

import com.dariobabic.githubrepos.features.user.fakeInfoModel
import com.dariobabic.githubrepos.features.user.fakeRepoModel
import com.dariobabic.githubrepos.features.user.presentation.use_case_factory.UserUseCaseContract
import com.dariobabic.githubrepos.features.user.presentation.use_case_factory.UserUseCaseFactory
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UserViewModelTests {

    @MockK
    lateinit var userUseCaseFactory: UserUseCaseFactory

    @MockK
    lateinit var useCase: UserUseCaseContract

    @MockK
    lateinit var callback: UserCallback

    private lateinit var viewModel: UserViewModel

    private val testScheduler = TestScheduler()
    private val ownerName = "dbabic89"

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        RxJavaPlugins.setIoSchedulerHandler { testScheduler }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { testScheduler }

        every { userUseCaseFactory.getUseCase(any(), any()) } returns useCase
        every { useCase.getInfo() } returns Observable.fromArray(listOf(fakeInfoModel))
        every { useCase.getRepos() } returns Observable.fromArray(listOf(fakeRepoModel))
        every { callback.startAuthorization() } returns Unit
        every { callback.logout() } returns Unit
        every { callback.onLinkClicked(any()) } returns Unit
    }

    @Test
    fun userViewModel_getInfo_verifyFields() {
        // GIVEN
        val expectedAvatarUrl = "https://avatars.githubusercontent.com/u/13087441?v=4"
        val expectedBio =
            "In west Philadelphia born and raised On the playground was where I spent most of my days"
        val expectedBlog = "medium/dbabic89"
        val expectedCompany = "Virgin Pulse"
        val expectedCreatedAt = "28.1.2022."
        val expectedFollowers = 999
        val expectedFollowerCount = 999
        val expectedFollowing = 1
        val expectedLocation = "Zepce"
        val expectedPublicReposCount = 99

        // WHEN
        viewModel = UserViewModel(userUseCaseFactory, ownerName, false)
        useCase.getInfo()
            // THEN
            .doOnNext {
                assertEquals(expectedAvatarUrl, viewModel.avatarUrl)
                assertEquals(expectedBio, viewModel.bio)
                assertEquals(expectedBlog, viewModel.blog)
                assertEquals(expectedCompany, viewModel.company)
                assertEquals(expectedCreatedAt, viewModel.createdAt)
                assertEquals(expectedFollowers, viewModel.followers)
                assertEquals(expectedFollowerCount, viewModel.followersCount)
                assertEquals(expectedFollowing, viewModel.following)
                assertEquals(expectedLocation, viewModel.location)
                assertEquals(expectedPublicReposCount, viewModel.publicReposCount)

                assertTrue(viewModel.displayInfo)
                assertFalse(viewModel.loginVisible)
                assertTrue(viewModel.logoutVisible)
            }
    }

    @Test
    fun userViewModel_getRepos_returnsCompleted() {
        // GIVEN
        val expectedModel = listOf(fakeRepoModel)

        // WHEN
        viewModel = UserViewModel(userUseCaseFactory, ownerName, false)
        useCase.getRepos()
            .test()
            // THEN
            .assertValue(expectedModel)
            .assertComplete()
    }

    @Test
    fun ownerViewModel_getInfo_verifyFields() {
        // GIVEN
        val expectedAvatarUrl = "https://avatars.githubusercontent.com/u/13087441?v=4"
        val expectedBio =
            "In west Philadelphia born and raised On the playground was where I spent most of my days"
        val expectedBlog = "medium/dbabic89"
        val expectedCompany = "Virgin Pulse"
        val expectedCreatedAt = "28.1.2022."
        val expectedFollowers = 999
        val expectedFollowerCount = 999
        val expectedFollowing = 1
        val expectedLocation = "Zepce"
        val expectedPublicReposCount = 99

        // WHEN
        viewModel = UserViewModel(userUseCaseFactory, ownerName, true)
        useCase.getInfo()
            // THEN
            .doOnNext {
                assertEquals(expectedAvatarUrl, viewModel.avatarUrl)
                assertEquals(expectedBio, viewModel.bio)
                assertEquals(expectedBlog, viewModel.blog)
                assertEquals(expectedCompany, viewModel.company)
                assertEquals(expectedCreatedAt, viewModel.createdAt)
                assertEquals(expectedFollowers, viewModel.followers)
                assertEquals(expectedFollowerCount, viewModel.followersCount)
                assertEquals(expectedFollowing, viewModel.following)
                assertEquals(expectedLocation, viewModel.location)
                assertEquals(expectedPublicReposCount, viewModel.publicReposCount)

                assertTrue(viewModel.displayInfo)
                assertFalse(viewModel.loginVisible)
                assertFalse(viewModel.logoutVisible)
            }
    }

    @Test
    fun ownerViewModel_getRepos_returnsCompleted() {
        // GIVEN
        val expectedModel = listOf(fakeRepoModel)

        // WHEN
        viewModel = UserViewModel(userUseCaseFactory, ownerName, true)
        useCase.getRepos()
            .test()
            // THEN
            .assertValue(expectedModel)
            .assertComplete()
    }

    @Test
    fun viewModel_clickDetailsLink_opensLink() {
        // GIVEN
        viewModel = UserViewModel(userUseCaseFactory, ownerName, true)
        viewModel.callback = callback
        // WHEN
        viewModel.openDetailsLink()
        // THEN
        verify(exactly = 1) { callback.onLinkClicked(any()) }
    }

    @Test
    fun viewModel_clickLogin_opensLink() {
        // GIVEN
        viewModel = UserViewModel(userUseCaseFactory, ownerName, true)
        viewModel.callback = callback
        // WHEN
        viewModel.login()
        // THEN
        verify(exactly = 1) { callback.startAuthorization() }
    }

    @Test
    fun viewModel_clickLogout_opensLink() {
        // GIVEN
        viewModel = UserViewModel(userUseCaseFactory, ownerName, true)
        viewModel.callback = callback
        // WHEN
        viewModel.logout()
        // THEN
        verify(exactly = 1) { callback.logout() }
    }

    @Test
    fun viewModel_clickBlogLink_opensLink() {
        // GIVEN
        viewModel = UserViewModel(userUseCaseFactory, ownerName, true)
        viewModel.callback = callback
        // WHEN
        viewModel.openBlogLink()
        // THEN
        verify(exactly = 1) { callback.onLinkClicked(any()) }
    }
}