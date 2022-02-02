package com.dariobabic.githubrepos.features.repos.data

import com.dariobabic.githubrepos.features.repos.data.remote.data_sources.RemoteDataSource
import com.dariobabic.githubrepos.features.repos.data.remote.data_sources.RemoteDataSourceContract
import com.dariobabic.githubrepos.features.repos.data.remote.services.ReposService
import com.dariobabic.githubrepos.features.repos.fakeSearchResponse
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class RemoteDataSourceTests {

    @MockK
    lateinit var service: ReposService

    private lateinit var remoteDataSource: RemoteDataSourceContract

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        remoteDataSource = RemoteDataSource(service)

        every { service.getSearchedRepos(any(), any()) } returns
                Observable.fromArray(fakeSearchResponse)
    }

    @Test
    fun remoteDataSource_getSearchedRepos_returnsListOf2() {
        // GIVEN
        val query = "android"
        val sort = "stars"
        val expected = fakeSearchResponse

        remoteDataSource
            // WHEN
            .getSearchedRepos(query, sort)
            .test()
            // THEN
            .assertValue(expected)
            .assertComplete()
    }

    @Test
    fun remoteDataSource_getSearchedRepos_verifyFunctionCalls() {
        // GIVEN
        val query = "android"
        val sort = "stars"

        remoteDataSource
            // WHEN
            .getSearchedRepos(query, sort)
            .test()
        // THEN
        verify(exactly = 1) { service.getSearchedRepos(any(), any()) }
    }
}