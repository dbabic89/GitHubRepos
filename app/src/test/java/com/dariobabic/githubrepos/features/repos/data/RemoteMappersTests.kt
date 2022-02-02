package com.dariobabic.githubrepos.features.repos.data

import com.dariobabic.githubrepos.features.repos.*
import com.dariobabic.githubrepos.features.repos.data.remote.mappers.mapReposResponseToModel
import com.dariobabic.githubrepos.features.repos.data.remote.mappers.mapSearchReposResponseToModel
import org.junit.Assert.assertEquals
import org.junit.Test

class RemoteMappersTests {

    @Test
    fun mapRepoResponse_passingEmptyList_returnEmptyList() {
        // GIVEN
        val responseList = fakeEmptyResponse
        val expected = fakeEmptyList
        // WHEN
        val actual = mapSearchReposResponseToModel(responseList)
        // THEN
        assertEquals(expected, actual)
    }

    @Test
    fun mapRepoResponse_passingListOf2_returnModelListOf2() {
        // GIVEN
        val responseList = fakeSearchResponse
        val expected = fakeModelListOf2
        // WHEN
        val actual = mapSearchReposResponseToModel(responseList)
        // THEN
        assertEquals(expected.size, actual.size)
    }

    @Test
    fun mapRepoResponse_passingListOf2_returnModelListOf1() {
        // GIVEN
        val responseList = fakeSearchResponseImportantFieldNull
        val expected = fakeModelListOf1
        // WHEN
        val actual = mapSearchReposResponseToModel(responseList)
        // THEN
        assertEquals(expected.size, actual.size)
    }

    @Test
    fun mapRepoResponse_passingResponse_checkingFields() {
        // GIVEN
        val response = fakeResponse1
        val expected = fakeModel1
        // WHEN
        val actual = mapReposResponseToModel(response)
        // THEN
        assertEquals(expected, actual)
    }
}