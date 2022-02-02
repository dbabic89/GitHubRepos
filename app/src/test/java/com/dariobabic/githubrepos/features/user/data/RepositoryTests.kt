package com.dariobabic.githubrepos.features.user.data

import com.dariobabic.githubrepos.features.user.*
import com.dariobabic.githubrepos.features.user.data.local.data_sources.LocalDataSourceContract
import com.dariobabic.githubrepos.features.user.data.remote.data_sources.RemoteDataSourceContract
import com.dariobabic.githubrepos.features.user.data.repositories.UserRepository
import com.dariobabic.githubrepos.features.user.domain.abstractions.UserRepositoryContract
import com.dariobabic.githubrepos.features.repos.fakeResponseList
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

    lateinit var repository: UserRepositoryContract

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        repository = UserRepository(localDataSource, remoteDataSource)

        every { localDataSource.saveUserInfo(any()) } returns Completable.complete()
        every { localDataSource.saveUserRepos(any()) } returns Completable.complete()
        every { localDataSource.loadUserInfo() } returns Observable.fromArray(listOf(fakeUserModel))
        every { localDataSource.loadUserRepos() } returns
                Observable.fromArray(listOf(fakeUserRepoModel))

        every { remoteDataSource.getUserInfo(any()) } returns Single.just(fakeUserResponse)
        every { remoteDataSource.getUserRepos(any()) } returns Single.just(fakeResponseList)
    }

    @Test
    fun repository_getUserDetails_returnsCompleted() {
        // GIVEN
        val accessToken = "1234"

        repository
            // WHEN
            .getUserDetails(accessToken)
            .test()
            // THEN
            .assertComplete()
    }

    @Test
    fun repository_getUserDetails_verifyOrder() {
        // GIVEN
        val accessToken = "1234"

        repository
            // WHEN
            .getUserDetails(accessToken)
            .test()
        // THEN
        verifyOrder {
            remoteDataSource.getUserInfo(any())
            localDataSource.saveUserInfo(any())
            remoteDataSource.getUserRepos(any())
            localDataSource.saveUserRepos(any())
        }
    }

    @Test
    fun repository_getUserDetails_verifyFunctionCalls() {
        // GIVEN
        val accessToken = "1234"

        repository
            // WHEN
            .getUserDetails(accessToken)
            .test()
        // THEN
        verify(exactly = 1) { remoteDataSource.getUserInfo(any()) }
        verify(exactly = 1) { localDataSource.saveUserInfo(any()) }
        verify(exactly = 1) { remoteDataSource.getUserRepos(any()) }
        verify(exactly = 1) { localDataSource.saveUserRepos(any()) }
    }

    @Test
    fun repository_loadUserInfo_returnsCompleted() {
        // GIVEN
        val expectedEntity = listOf(fakeUserEntity)
        repository
            // WHEN
            .loadUserInfo()
            .test()
            // THEN
            .assertValue(expectedEntity)
            .assertComplete()
    }

    @Test
    fun repository_loadUserInfo_verifyFunctionCalls() {
        // GIVEN
        repository
            // WHEN
            .loadUserInfo()
            .test()
        // THEN
        verify(exactly = 1) { localDataSource.loadUserInfo() }
    }

    @Test
    fun repository_loadUserRepos_returnsCompleted() {
        // GIVEN
        val expected = listOf(fakeUserRepoEntity)
        repository
            // WHEN
            .loadUserRepos()
            .test()
            // THEN
            .assertValue(expected)
            .assertComplete()
    }

    @Test
    fun repository_loadUserRepos_verifyFunctionCalls() {
        // GIVEN
        repository
            // WHEN
            .loadUserRepos()
            .test()
        // THEN
        verify(exactly = 1) { localDataSource.loadUserRepos() }
    }
}