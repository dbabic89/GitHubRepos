package com.dariobabic.githubrepos.features.owner.data

import com.dariobabic.githubrepos.features.owner.*
import com.dariobabic.githubrepos.features.owner.data.local.data_sources.LocalDataSourceContract
import com.dariobabic.githubrepos.features.owner.data.remote.data_sources.RemoteDataSourceContract
import com.dariobabic.githubrepos.features.owner.data.repositories.OwnerRepository
import com.dariobabic.githubrepos.features.owner.domain.abstractions.OwnerRepositoryContract
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

    lateinit var repository: OwnerRepositoryContract

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        repository = OwnerRepository(localDataSource, remoteDataSource)

        every { localDataSource.saveOwnerInfo(any()) } returns Completable.complete()
        every { localDataSource.saveOwnerRepos(any()) } returns Completable.complete()
        every { localDataSource.removeOwnerRepos() } returns Completable.complete()
        every { localDataSource.loadOwnerInfo() } returns Single.just(fakeOwnerModel)
        every { localDataSource.loadOwnerRepos() } returns
                Observable.fromArray(listOf(fakeOwnerRepoModel))

        every { remoteDataSource.getOwnerInfo(any()) } returns Single.just(fakeOwnerResponse)
        every { remoteDataSource.getOwnerRepos(any()) } returns Single.just(fakeResponseList)
    }

    @Test
    fun repository_getOwnerDetails_returnsCompleted() {
        // GIVEN
        val ownerName = "dbabic89"

        repository
            // WHEN
            .getOwnerDetails(ownerName)
            .test()
            // THEN
            .assertComplete()
    }

    @Test
    fun repository_getOwnerDetails_verifyOrder() {
        // GIVEN
        val ownerName = "dbabic89"

        repository
            // WHEN
            .getOwnerDetails(ownerName)
            .test()
        // THEN
        verifyOrder {
            remoteDataSource.getOwnerInfo(any())
            remoteDataSource.getOwnerRepos(any())
            localDataSource.saveOwnerInfo(any())
            localDataSource.saveOwnerRepos(any())
        }
    }

    @Test
    fun repository_getOwnerDetails_verifyFunctionCalls() {
        // GIVEN
        val ownerName = "dbabic89"

        repository
            // WHEN
            .getOwnerDetails(ownerName)
            .test()
        // THEN
        verify(exactly = 1) { remoteDataSource.getOwnerInfo(any()) }
        verify(exactly = 1) { remoteDataSource.getOwnerRepos(any()) }
        verify(exactly = 1) { localDataSource.saveOwnerInfo(any()) }
        verify(exactly = 1) { localDataSource.saveOwnerRepos(any()) }
    }

    @Test
    fun repository_loadOwnerInfo_returnsCompleted() {
        // GIVEN
        val expectedEntity = fakeOwnerEntity
        repository
            // WHEN
            .loadOwnerInfo()
            .test()
            // THEN
            .assertValue(expectedEntity)
            .assertComplete()
    }

    @Test
    fun repository_loadOwnerInfo_verifyFunctionCalls() {
        // GIVEN
        repository
            // WHEN
            .loadOwnerInfo()
            .test()
        // THEN
        verify(exactly = 1) { localDataSource.loadOwnerInfo() }
    }

    @Test
    fun repository_loadOwnerRepos_returnsCompleted() {
        // GIVEN
        val expected = listOf(fakeOwnerRepoEntity)
        repository
            // WHEN
            .loadOwnerRepos()
            .test()
            // THEN
            .assertValue(expected)
            .assertComplete()
    }

    @Test
    fun repository_loadOwnerRepos_verifyOrder() {
        // GIVEN
        repository
            // WHEN
            .loadOwnerRepos()
            .test()
        // THEN
        verifyOrder {
            localDataSource.removeOwnerRepos()
            localDataSource.loadOwnerRepos()
        }
    }

    @Test
    fun repository_loadOwnerRepos_verifyFunctionCalls() {
        // GIVEN
        repository
            // WHEN
            .loadOwnerRepos()
            .test()
        // THEN
        verify(exactly = 1) { localDataSource.removeOwnerRepos() }
        verify(exactly = 1) { localDataSource.loadOwnerRepos() }
    }
}