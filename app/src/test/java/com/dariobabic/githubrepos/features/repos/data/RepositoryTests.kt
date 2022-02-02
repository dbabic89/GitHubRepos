package com.dariobabic.githubrepos.features.repos.data

import com.dariobabic.githubrepos.features.repos.data.local.data_sources.LocalDataSourceContract
import com.dariobabic.githubrepos.features.repos.data.remote.data_sources.RemoteDataSourceContract
import com.dariobabic.githubrepos.features.repos.data.repositories.ReposRepository
import com.dariobabic.githubrepos.features.repos.fakeEntity1
import com.dariobabic.githubrepos.features.repos.fakeModel1
import com.dariobabic.githubrepos.features.repos.fakeModelListOf2
import com.dariobabic.githubrepos.features.repos.fakeSearchResponse
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

class RepositoryTests {

    @MockK
    lateinit var localDataSource: LocalDataSourceContract

    @MockK
    lateinit var remoteDataSource: RemoteDataSourceContract

    private lateinit var repository: ReposRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        repository = ReposRepository(localDataSource, remoteDataSource)

        every { localDataSource.clearRepos() } returns Completable.complete()
        every { localDataSource.saveRepos(any()) } returns Completable.complete()
        every { localDataSource.loadRepos() } returns
                Observable.fromArray(fakeModelListOf2)
        every { localDataSource.loadRepo(any()) } returns Single.just(fakeModel1)

        every { remoteDataSource.getSearchedRepos(any(), any()) } returns
                Observable.fromArray(fakeSearchResponse)
    }

    @Test
    fun repository_getSearchedRepos_returnsCompleted() {
        // GIVEN
        val query = "android"
        val sort = "stars"

        repository
            // WHEN
            .getSearchedRepos(query, sort)
            .test()
            // THEN
            .assertComplete()
    }

    @Test
    fun repository_getSearchedRepos_verifyOrder() {
        // GIVEN
        val query = "android"
        val sort = "stars"

        repository
            // WHEN
            .getSearchedRepos(query, sort)
            .test()
        // THEN
        verifyOrder {
            remoteDataSource.getSearchedRepos(any(), any())
            localDataSource.saveRepos(any())
        }
    }

    @Test
    fun repository_getSearchedRepos_verifyFunctionCalls() {
        // GIVEN
        val query = "android"
        val sort = "stars"

        repository
            // WHEN
            .getSearchedRepos(query, sort)
            .test()
        // THEN
        verify(exactly = 1) { remoteDataSource.getSearchedRepos(any(), any()) }
        verify(exactly = 1) { localDataSource.saveRepos(any()) }
    }

    @Test
    fun repository_clearRepos_returnsCompleted() {
        // GIVEN
        repository
            // WHEN
            .clearRepos()
            .test()
            // THEN
            .assertComplete()
    }

    @Test
    fun repository_clearRepos_verifyFunctionCalls() {
        // GIVEN
        repository
            // WHEN
            .clearRepos()
            .test()
        // THEN
        verify(exactly = 1) { localDataSource.clearRepos() }
    }

    @Test
    fun repository_loadRepos_returnsCompleted() {
        // GIVEN
        val name = "GitHubRepos"
        val entity = fakeEntity1

        repository
            // WHEN
            .loadRepo(name)
            .test()
            // THEN
            .assertValue(entity)
            .assertComplete()
    }

    @Test
    fun repository_loadRepos_verifyFunctionCalls() {
        // GIVEN
        val name = "GitHubRepos"

        repository
            // WHEN
            .loadRepo(name)
            .test()
        // THEN
        verify(exactly = 1) { localDataSource.loadRepo(any()) }
    }
}