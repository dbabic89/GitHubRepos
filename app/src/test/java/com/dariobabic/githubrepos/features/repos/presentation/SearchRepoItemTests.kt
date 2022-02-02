package com.dariobabic.githubrepos.features.repos.presentation

import com.dariobabic.githubrepos.features.repos.fakeEntity1
import com.dariobabic.githubrepos.features.repos.presentation.search.adapter.ItemClickListener
import com.dariobabic.githubrepos.features.repos.presentation.search.adapter.SearchRepoItem
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SearchRepoItemTests {

    @MockK
    lateinit var clickListener: ItemClickListener

    private lateinit var searchRepoItem: SearchRepoItem

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        searchRepoItem = SearchRepoItem(fakeEntity1, clickListener)

        every { clickListener.onOwnerClicked(any()) } returns Unit
        every { clickListener.onRepoClicked(any()) } returns Unit
    }

    @Test
    fun searchRepoItem_passingModel_checkingFields() {
        // GIVEN
        val expectedWatcherCount = "999"
        val expectedForksCount = "999"
        val expectedIssuesCount = "0"
        // WHEN
        val item = searchRepoItem
        // THEN
        Assert.assertEquals(expectedWatcherCount, item.watcherCount)
        Assert.assertEquals(expectedForksCount, item.forkCount)
        Assert.assertEquals(expectedIssuesCount, item.issueCount)
    }

    @Test
    fun searchRepoItem_openOwnerDetails_triggersOwnerClicked() {
        // GIVEN
        searchRepoItem = SearchRepoItem(fakeEntity1, clickListener)
        // WHEN
        searchRepoItem.openOwnerDetails()
        // THEN
        verify(exactly = 1) { clickListener.onOwnerClicked(any()) }
    }

    @Test
    fun searchRepoItem_openRepoDetails_triggersRepoClicked() {
        // GIVEN
        searchRepoItem = SearchRepoItem(fakeEntity1, clickListener)
        // WHEN
        searchRepoItem.openRepoDetails()
        // THEN
        verify(exactly = 1) { clickListener.onRepoClicked(any()) }
    }
}