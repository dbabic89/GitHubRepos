package com.dariobabic.githubrepos.features.repos.presentation

import com.dariobabic.githubrepos.core.constants.EMPTY_STRING
import com.dariobabic.githubrepos.core.constants.SORT_BY_UPDATED
import com.dariobabic.githubrepos.features.repos.domain.use_cases.ClearReposUseCase
import com.dariobabic.githubrepos.features.repos.domain.use_cases.GetSearchReposUseCase
import com.dariobabic.githubrepos.features.repos.domain.use_cases.SortReposUseCase
import com.dariobabic.githubrepos.features.repos.fakeEntityListOf2
import com.dariobabic.githubrepos.features.repos.presentation.search.SearchReposCallback
import com.dariobabic.githubrepos.features.repos.presentation.search.SearchReposViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class SearchReposViewModelTests {

    @MockK
    lateinit var getSearchReposUseCase: GetSearchReposUseCase

    @MockK
    lateinit var sortReposUseCase: SortReposUseCase

    @MockK
    lateinit var clearReposUseCase: ClearReposUseCase

    @MockK
    lateinit var callback: SearchReposCallback

    private lateinit var viewModel: SearchReposViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        viewModel = SearchReposViewModel(getSearchReposUseCase, sortReposUseCase, clearReposUseCase)

        every { getSearchReposUseCase.setup(any()) } returns getSearchReposUseCase
        every { getSearchReposUseCase.execute(any()) } returns Unit
        every { getSearchReposUseCase.buildUseCaseObservable() } returns
                Observable.fromArray(fakeEntityListOf2)

        every { sortReposUseCase.setup(any()) } returns sortReposUseCase
        every { sortReposUseCase.execute(any()) } returns Unit
        every { sortReposUseCase.buildUseCaseObservable() } returns
                Observable.fromArray(fakeEntityListOf2)

        every { clearReposUseCase.execute(any()) } returns Unit
        every { clearReposUseCase.buildUseCaseCompletable() } returns Completable.complete()

        every { callback.openRepo(any()) } returns Unit
        every { callback.openOwner(any()) } returns Unit
    }

    @Test
    fun viewModel_searchRepos_returnsCompleted() {
        // GIVEN
        val expected = fakeEntityListOf2
        // WHEN
        getSearchReposUseCase
            .buildUseCaseObservable()
            .test()
            // THEN
            .assertValue(expected)
            .assertComplete()
    }

    @Test
    fun viewModel_sortRepos_returnsCompleted() {
        // GIVEN
        val expected = fakeEntityListOf2
        val sortBy = SORT_BY_UPDATED
        // WHEN
        sortReposUseCase
            .setup(sortBy)
            .buildUseCaseObservable()
            .test()
            // THEN
            .assertValue(expected)
            .assertComplete()
    }

    @Test
    fun viewModel_clickBlogLink_opensLink() {
        // GIVEN
        viewModel.callback = callback
        // WHEN
        viewModel.onRepoClicked(EMPTY_STRING)
        // THEN
        verify(exactly = 1) { callback.openRepo(any()) }
    }

    @Test
    fun viewModel_onOwnerClicked_opensLink() {
        // GIVEN
        viewModel.callback = callback
        // WHEN
        viewModel.onOwnerClicked(EMPTY_STRING)
        // THEN
        verify(exactly = 1) { callback.openOwner(any()) }
    }
}