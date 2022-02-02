package com.dariobabic.githubrepos.features.repos.data

import com.dariobabic.githubrepos.features.repos.data.local.data_sources.LocalDataSource
import com.dariobabic.githubrepos.features.repos.data.local.data_sources.LocalDataSourceContract
import com.dariobabic.githubrepos.features.repos.data.local.daos.ReposDao
import com.dariobabic.githubrepos.features.repos.fakeModel1
import com.dariobabic.githubrepos.features.repos.fakeModelListOf2
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

class LocalDataSourceTests {

    @MockK
    lateinit var dao: ReposDao

    private lateinit var localDataSource: LocalDataSourceContract

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        localDataSource = LocalDataSource(dao)

        every { dao.removeRepos() } returns Completable.complete()
        every { dao.saveRepos(any()) } returns Completable.complete()
        every { dao.loadRepos() } returns Observable.fromArray(fakeModelListOf2)
        every { dao.loadRepo(any()) } returns Single.just(fakeModel1)
    }

    @Test
    fun localDataSource_clearRepos_returnsCompleted() {
        // GIVEN
        localDataSource
            // WHEN
            .clearRepos()
            .test()
            // THEN
            .assertComplete()
    }

    @Test
    fun localDataSource_clearRepos_verifyFunctionCalls() {
        // GIVEN
        localDataSource
            // WHEN
            .clearRepos()
            .test()
        // THEN
        verify(exactly = 1) { dao.removeRepos() }
    }

    @Test
    fun localDataSource_updateRepos_returnsCompleted() {
        // GIVEN
        val modelList = fakeModelListOf2

        localDataSource
            // WHEN
            .saveRepos(modelList)
            .test()
            // THEN
            .assertComplete()
    }

    @Test
    fun localDataSource_updateRepos_verifyOrder() {
        // GIVEN
        val modelList = fakeModelListOf2

        localDataSource
            // WHEN
            .saveRepos(modelList)
            .test()
        // THEN
        verifyOrder {
            dao.removeRepos()
            dao.saveRepos(any())
        }
    }

    @Test
    fun localDataSource_updateRepos_verifyFunctionCalls() {
        // GIVEN
        val modelList = fakeModelListOf2

        localDataSource
            // WHEN
            .saveRepos(modelList)
            .test()
        // THEN
        verify(exactly = 1) { dao.removeRepos() }
        verify(exactly = 1) { dao.saveRepos(any()) }
    }

    @Test
    fun localDataSource_loadRepos_returnsCompleted() {
        // GIVEN
        localDataSource
            // WHEN
            .loadRepos()
            .test()
            // THEN
            .assertComplete()
    }

    @Test
    fun localDataSource_loadRepos_verifyFunctionCalls() {
        // GIVEN
        localDataSource
            // WHEN
            .loadRepos()
            .test()
        // THEN
        verify(exactly = 1) { dao.loadRepos() }
    }

    @Test
    fun localDataSource_loadRepo_returnsCompleted() {
        // GIVEN
        val name = "GitHubRepos"
        val model = fakeModel1

        localDataSource
            // WHEN
            .loadRepo(name)
            .test()
            // THEN
            .assertValue(model)
            .assertComplete()
    }

    @Test
    fun localDataSource_loadRepo_verifyFunctionCalls() {
        // GIVEN
        val name = "GitHubRepos"

        localDataSource
            // WHEN
            .loadRepo(name)
            .test()
        // THEN
        verify(exactly = 1) { dao.loadRepo(any()) }
    }
}