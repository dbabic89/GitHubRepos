package com.dariobabic.githubrepos.features.user.presentation

import com.dariobabic.githubrepos.features.user.fakeRepoModel
import com.dariobabic.githubrepos.features.user.presentation.adapter.UserRepoItem
import org.junit.Assert.assertEquals
import org.junit.Test

class UserRepoItemTests {

    @Test
    fun userRepoItem_passingModel_checkingFields() {
        // GIVEN
        val model = fakeRepoModel

        val expectedWatcherCount = "999"
        val expectedForksCount = "99"
        val expectedIssuesCount = "0"
        // WHEN
        val item = UserRepoItem(model)
        // THEN
        assertEquals(expectedWatcherCount, item.watcherCount)
        assertEquals(expectedForksCount, item.forkCount)
        assertEquals(expectedIssuesCount, item.issueCount)
    }
}