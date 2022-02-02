package com.dariobabic.githubrepos.features.user.data

import com.dariobabic.githubrepos.features.user.data.local.daos.UserDao
import com.dariobabic.githubrepos.features.user.data.local.data_sources.LocalDataSource
import com.dariobabic.githubrepos.features.user.data.local.data_sources.LocalDataSourceContract
import com.dariobabic.githubrepos.features.user.fakeUserModel
import com.dariobabic.githubrepos.features.user.fakeUserRepoModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.mockk.verifyOrder
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class LocalDataSourceTests {

    @MockK
    lateinit var dao: UserDao

    private lateinit var localDataSource: LocalDataSourceContract

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        localDataSource = LocalDataSource(dao)

        every { dao.loadUser() } returns Observable.fromArray(listOf(fakeUserModel))
        every { dao.saveUser(any()) } returns Completable.complete()
        every { dao.loadUserRepos() } returns Observable.fromArray(listOf(fakeUserRepoModel))
        every { dao.saveUserRepos(any()) } returns Completable.complete()
        every { dao.removeUser() } returns Completable.complete()
        every { dao.removeUserRepos() } returns Completable.complete()
    }

    @Test
    fun localDataSource_loadUserInfo_returnsCompleted() {
        // GIVEN
        val expected = listOf(fakeUserModel)

        localDataSource
            // WHEN
            .loadUserInfo()
            .test()
            // THEN
            .assertValue(expected)
            .assertComplete()
    }

    @Test
    fun localDataSource_loadUserInfo_verifyFunctionCalls() {
        // GIVEN
        localDataSource
            // WHEN
            .loadUserInfo()
            .test()
        // THEN
        verify(exactly = 1) { dao.loadUser() }
    }

    @Test
    fun localDataSource_saveUserInfo_returnsCompleted() {
        // GIVEN
        val model = fakeUserModel

        localDataSource
            // WHEN
            .saveUserInfo(model)
            .test()
            // THEN
            .assertComplete()
    }

    @Test
    fun localDataSource_saveUserInfo_verifyOrder() {
        // GIVEN
        val model = fakeUserModel

        localDataSource
            // WHEN
            .saveUserInfo(model)
            .test()
        // THEN
        verifyOrder {
            dao.removeUser()
            dao.saveUser(any())
        }
    }

    @Test
    fun localDataSource_saveUserInfo_verifyFunctionCalls() {
        // GIVEN
        val model = fakeUserModel

        localDataSource
            // WHEN
            .saveUserInfo(model)
            .test()
        // THEN
        verify(exactly = 1) { dao.removeUser() }
        verify(exactly = 1) { dao.saveUser(any()) }
    }

    @Test
    fun localDataSource_loadUserRepos_returnsCompleted() {
        // GIVEN
        val model = listOf(fakeUserRepoModel)

        localDataSource
            // WHEN
            .loadUserRepos()
            .test()
            // THEN
            .assertValue(model)
            .assertComplete()
    }

    @Test
    fun localDataSource_loadUserRepos_verifyFunctionCalls() {
        // GIVEN
        localDataSource
            // WHEN
            .loadUserRepos()
            .test()
        // THEN
        verify(exactly = 1) { dao.loadUserRepos() }
    }

    @Test
    fun localDataSource_saveUserRepos_returnsCompleted() {
        // GIVEN
        val model = listOf(fakeUserRepoModel)

        localDataSource
            // WHEN
            .saveUserRepos(model)
            .test()
            // THEN
            .assertComplete()
    }

    @Test
    fun localDataSource_saveUserRepos_verifyOrder() {
        // GIVEN
        val model = listOf(fakeUserRepoModel)

        localDataSource
            // WHEN
            .saveUserRepos(model)
            .test()
        // THEN
        verifyOrder {
            dao.removeUserRepos()
            dao.saveUserRepos(any())
        }
    }

    @Test
    fun localDataSource_saveUserRepos_verifyFunctionCalls() {
        // GIVEN
        val model = listOf(fakeUserRepoModel)

        localDataSource
            // WHEN
            .saveUserRepos(model)
            .test()
        // THEN
        verify(exactly = 1) { dao.removeUserRepos() }
        verify(exactly = 1) { dao.saveUserRepos(any()) }
    }
}