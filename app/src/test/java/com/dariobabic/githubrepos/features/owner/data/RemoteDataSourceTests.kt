package com.dariobabic.githubrepos.features.owner.data

import com.dariobabic.githubrepos.features.owner.data.remote.services.OwnerService
import com.dariobabic.githubrepos.features.owner.data.remote.data_sources.RemoteDataSource
import com.dariobabic.githubrepos.features.owner.data.remote.data_sources.RemoteDataSourceContract
import com.dariobabic.githubrepos.features.owner.fakeOwnerResponse
import com.dariobabic.githubrepos.features.repos.fakeResponseList
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class RemoteDataSourceTests {

    @MockK
    lateinit var service: OwnerService

    private lateinit var remoteDataSource: RemoteDataSourceContract

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        remoteDataSource = RemoteDataSource(service)

        every { service.getOwnerInfo(any()) } returns Single.just(fakeOwnerResponse)
        every { service.getOwnerRepos(any()) } returns Single.just(fakeResponseList)
    }

    @Test
    fun remoteDataSource_getOwnerInfo_returnsCompleted() {
        // GIVEN
        val ownerName = "dbabic89"
        val expected = fakeOwnerResponse

        remoteDataSource
            // WHEN
            .getOwnerInfo(ownerName)
            .test()
            // THEN
            .assertValue(expected)
            .assertComplete()
    }

    @Test
    fun remoteDataSource_getOwnerInfo_verifyFunctionCalls() {
        // GIVEN
        val ownerName = "dbabic89"

        remoteDataSource
            // WHEN
            .getOwnerInfo(ownerName)
            .test()
        // THEN
        verify(exactly = 1) { service.getOwnerInfo(any()) }
    }

    @Test
    fun remoteDataSource_getOwnerRepos_returnsCompleted() {
        // GIVEN
        val ownerName = "dbabic89"
        val expected = fakeResponseList

        remoteDataSource
            // WHEN
            .getOwnerRepos(ownerName)
            .test()
            // THEN
            .assertValue(expected)
            .assertComplete()
    }

    @Test
    fun remoteDataSource_getOwnerRepos_verifyFunctionCalls() {
        // GIVEN
        val ownerName = "dbabic89"

        remoteDataSource
            // WHEN
            .getOwnerRepos(ownerName)
            .test()
        // THEN
        verify(exactly = 1) { service.getOwnerRepos(any()) }
    }
}