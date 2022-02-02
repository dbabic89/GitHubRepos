package com.dariobabic.githubrepos.features.owner.data

import com.dariobabic.githubrepos.features.owner.data.local.daos.OwnerDao
import com.dariobabic.githubrepos.features.owner.data.local.data_sources.LocalDataSource
import com.dariobabic.githubrepos.features.owner.data.local.data_sources.LocalDataSourceContract
import com.dariobabic.githubrepos.features.owner.fakeOwnerModel
import com.dariobabic.githubrepos.features.owner.fakeOwnerRepoModel
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
    lateinit var dao: OwnerDao

    private lateinit var localDataSource: LocalDataSourceContract

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        localDataSource = LocalDataSource(dao)

        every { dao.loadOwner() } returns Single.just(fakeOwnerModel)
        every { dao.saveOwner(any()) } returns Completable.complete()
        every { dao.loadOwnerRepos() } returns Observable.fromArray(listOf(fakeOwnerRepoModel))
        every { dao.saveOwnerRepos(any()) } returns Completable.complete()
        every { dao.removeOwner() } returns Completable.complete()
        every { dao.removeOwnerRepos() } returns Completable.complete()
    }

    @Test
    fun localDataSource_loadOwnerInfo_returnsCompleted() {
        // GIVEN
        val model = fakeOwnerModel

        localDataSource
            // WHEN
            .loadOwnerInfo()
            .test()
            // THEN
            .assertValue(model)
            .assertComplete()
    }

    @Test
    fun localDataSource_loadOwnerInfo_verifyFunctionCalls() {
        // GIVEN
        localDataSource
            // WHEN
            .loadOwnerInfo()
            .test()
        // THEN
        verify(exactly = 1) { dao.loadOwner() }
    }

    @Test
    fun localDataSource_saveOwnerInfo_returnsCompleted() {
        // GIVEN
        val model = fakeOwnerModel

        localDataSource
            // WHEN
            .saveOwnerInfo(model)
            .test()
            // THEN
            .assertComplete()
    }

    @Test
    fun localDataSource_saveOwnerInfo_verifyOrder() {
        // GIVEN
        val model = fakeOwnerModel

        localDataSource
            // WHEN
            .saveOwnerInfo(model)
            .test()
        // THEN
        verifyOrder {
            dao.removeOwner()
            dao.saveOwner(any())
        }
    }

    @Test
    fun localDataSource_saveOwnerInfo_verifyFunctionCalls() {
        // GIVEN
        val model = fakeOwnerModel

        localDataSource
            // WHEN
            .saveOwnerInfo(model)
            .test()
        // THEN
        verify(exactly = 1) { dao.removeOwner() }
        verify(exactly = 1) { dao.saveOwner(any()) }
    }

    @Test
    fun localDataSource_loadOwnerRepos_returnsCompleted() {
        // GIVEN
        val model = listOf(fakeOwnerRepoModel)

        localDataSource
            // WHEN
            .loadOwnerRepos()
            .test()
            // THEN
            .assertValue(model)
            .assertComplete()
    }

    @Test
    fun localDataSource_loadOwnerRepos_verifyFunctionCalls() {
        // GIVEN
        localDataSource
            // WHEN
            .loadOwnerRepos()
            .test()
        // THEN
        verify(exactly = 1) { dao.loadOwnerRepos() }
    }

    @Test
    fun localDataSource_saveOwnerRepos_returnsCompleted() {
        // GIVEN
        val model = listOf(fakeOwnerRepoModel)

        localDataSource
            // WHEN
            .saveOwnerRepos(model)
            .test()
            // THEN
            .assertComplete()
    }

    @Test
    fun localDataSource_saveOwnerRepos_verifyOrder() {
        // GIVEN
        val model = listOf(fakeOwnerRepoModel)

        localDataSource
            // WHEN
            .saveOwnerRepos(model)
            .test()
        // THEN
        verifyOrder {
            dao.removeOwnerRepos()
            dao.saveOwnerRepos(any())
        }
    }

    @Test
    fun localDataSource_saveOwnerRepos_verifyFunctionCalls() {
        // GIVEN
        val model = listOf(fakeOwnerRepoModel)

        localDataSource
            // WHEN
            .saveOwnerRepos(model)
            .test()
        // THEN
        verify(exactly = 1) { dao.removeOwnerRepos() }
        verify(exactly = 1) { dao.saveOwnerRepos(any()) }
    }

    @Test
    fun localDataSource_removeOwnerRepos_returnsCompleted() {
        // GIVEN
        localDataSource
            // WHEN
            .removeOwnerRepos()
            .test()
            // THEN
            .assertComplete()
    }

    @Test
    fun localDataSource_removeOwnerRepos_verifyFunctionCalls() {
        // GIVEN
        localDataSource
            // WHEN
            .removeOwnerRepos()
            .test()
        // THEN
        verify(exactly = 1) { dao.removeOwnerRepos() }
    }
}