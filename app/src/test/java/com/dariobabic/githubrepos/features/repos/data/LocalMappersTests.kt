package com.dariobabic.githubrepos.features.repos.data

import com.dariobabic.githubrepos.features.repos.data.local.mappers.mapRepoModelListToEntityList
import com.dariobabic.githubrepos.features.repos.data.local.mappers.mapRepoModelToEntity
import com.dariobabic.githubrepos.features.repos.*
import org.junit.Assert.assertEquals
import org.junit.Test

class LocalMappersTests {

    @Test
    fun mapRepoModelList_passingEmptyList_returnEmptyList() {
        // GIVEN
        val responseList = fakeEmptyModel
        val expected = fakeEmptyEntity
        // WHEN
        val actual = mapRepoModelListToEntityList(responseList)
        // THEN
        assertEquals(expected, actual)
    }

    @Test
    fun mapRepoModelList_passingListOf2_returnModelListOf2() {
        // GIVEN
        val modelList = fakeModelListOf2
        val expected = fakeEntityListOf2
        // WHEN
        val actual = mapRepoModelListToEntityList(modelList)
        // THEN
        assertEquals(expected.size, actual.size)
    }

    @Test
    fun mapRepoModel_passingModel_checkingFields() {
        // GIVEN
        val model = fakeModel1
        val expected = fakeEntity1
        // WHEN
        val actual = mapRepoModelToEntity(model)
        // THEN
        assertEquals(expected, actual)
    }
}