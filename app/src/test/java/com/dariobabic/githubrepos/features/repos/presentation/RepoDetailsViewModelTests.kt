package com.dariobabic.githubrepos.features.repos.presentation

import com.dariobabic.githubrepos.features.repos.domain.use_cases.LoadRepoUseCase
import com.dariobabic.githubrepos.features.repos.fakeEntity1
import com.dariobabic.githubrepos.features.repos.presentation.details.RepoDetailsCallback
import com.dariobabic.githubrepos.features.repos.presentation.details.RepoDetailsViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RepoDetailsViewModelTests {

    @MockK
    lateinit var useCase: LoadRepoUseCase

    @MockK
    lateinit var callback: RepoDetailsCallback

    private lateinit var viewModel: RepoDetailsViewModel

    private val name = "GitHubRepos"

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        every { useCase.setup(any()) } returns useCase
        every { useCase.execute(any()) } returns Unit
        every { useCase.buildUseCaseSingle() } returns Single.just(fakeEntity1)

        viewModel = RepoDetailsViewModel(useCase, name)

        every { callback.openOwner(any()) } returns Unit
        every { callback.openRepoLink(any()) } returns Unit
    }

    @Test
    fun viewModel_loadRepoDetails_returnsCompleted() {
        // GIVEN
        val entity = fakeEntity1

        // WHEN
        useCase
            .setup(name)
            .buildUseCaseSingle()
            .test()
            // THEN
            .assertValue(entity)
            .assertComplete()
    }

    @Test
    fun viewModel_loadDetails_verifyFields() {
        // GIVEN
        val expectedLanguage = "kotlin"
        val expectedDescription = "Android task for Ministry Of Programming"
        val expectedCreatedAt = "28.1.2022."
        val expectedUpdatedAt = "28.1.2022."
        val expectedOwnerName = "dbabic89"
        val expectedOwnerAvatarUrl = "https://avatars.githubusercontent.com/u/13087441?v=4"
        val expectedWatchers = "999"
        val expectedWatchersCount = 999
        val expectedForks = "999"
        val expectedForkCount = 999
        val expectedIssues = "0"
        val expectedIssueCount = 0

        // WHEN
        useCase
            .setup(name)
            .buildUseCaseSingle()
            // THEN
            .doOnSuccess {
                assertEquals(expectedLanguage, viewModel.language)
                assertEquals(expectedDescription, viewModel.description)
                assertEquals(expectedCreatedAt, viewModel.createdAt)
                assertEquals(expectedUpdatedAt, viewModel.updatedAt)
                assertEquals(expectedOwnerName, viewModel.ownerName)
                assertEquals(expectedOwnerAvatarUrl, viewModel.ownerAvatarUrl)
                assertEquals(expectedWatchers, viewModel.watchers)
                assertEquals(expectedWatchersCount, viewModel.watcherCount)
                assertEquals(expectedForks, viewModel.forks)
                assertEquals(expectedForkCount, viewModel.forkCount)
                assertEquals(expectedIssues, viewModel.issues)
                assertEquals(expectedIssueCount, viewModel.issueCount)
            }
    }

    @Test
    fun viewModel_clickOwnerDetailsLink_opensLink() {
        // GIVEN
        viewModel.callback = callback
        // WHEN
        viewModel.openRepoDetailsLink()
        // THEN
        verify(exactly = 1) { callback.openRepoLink(any()) }
    }

    @Test
    fun viewModel_clickBlogLink_opensLink() {
        // GIVEN
        viewModel.callback = callback
        // WHEN
        viewModel.openOwnerDetails()
        // THEN
        verify(exactly = 1) { callback.openOwner(any()) }
    }
}