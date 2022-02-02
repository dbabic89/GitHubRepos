package com.dariobabic.githubrepos.features.user.data

import com.dariobabic.githubrepos.features.user.data.remote.services.UserService
import com.dariobabic.githubrepos.features.user.data.remote.data_sources.RemoteDataSource
import com.dariobabic.githubrepos.features.user.data.remote.data_sources.RemoteDataSourceContract
import com.dariobabic.githubrepos.features.user.fakeUserResponse
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
    lateinit var service: UserService

    private lateinit var remoteDataSource: RemoteDataSourceContract

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        remoteDataSource = RemoteDataSource(service)

        every { service.getUserInfo(any()) } returns Single.just(fakeUserResponse)
        every { service.getUserRepos(any()) } returns Single.just(fakeResponseList)
    }

    @Test
    fun remoteDataSource_getUserInfo_returnsCompleted() {
        // GIVEN
        val accessToken = "1234"
        val expected = fakeUserResponse

        remoteDataSource
            // WHEN
            .getUserInfo(accessToken)
            .test()
            // THEN
            .assertValue(expected)
            .assertComplete()
    }

    @Test
    fun remoteDataSource_getUserInfo_verifyFunctionCalls() {
        // GIVEN
        val accessToken = "1234"

        remoteDataSource
            // WHEN
            .getUserInfo(accessToken)
            .test()
        // THEN
        verify(exactly = 1) { service.getUserInfo(any()) }
    }

    @Test
    fun remoteDataSource_getUserRepos_returnsCompleted() {
        // GIVEN
        val accessToken = "1234"
        val expected = fakeResponseList

        remoteDataSource
            // WHEN
            .getUserRepos(accessToken)
            .test()
            // THEN
            .assertValue(expected)
            .assertComplete()
    }

    @Test
    fun remoteDataSource_getUserRepos_verifyFunctionCalls() {
        // GIVEN
        val accessToken = "1234"

        remoteDataSource
            // WHEN
            .getUserRepos(accessToken)
            .test()
        // THEN
        verify(exactly = 1) { service.getUserRepos(any()) }
    }
}