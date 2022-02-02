package com.dariobabic.githubrepos.features.user.data

import com.dariobabic.githubrepos.features.user.data.local.mappers.mapUserModelToEntity
import com.dariobabic.githubrepos.features.user.data.local.mappers.mapUserRepoModelToEntity
import com.dariobabic.githubrepos.features.user.fakeUserEntity
import com.dariobabic.githubrepos.features.user.fakeUserModel
import com.dariobabic.githubrepos.features.user.fakeUserRepoEntity
import com.dariobabic.githubrepos.features.user.fakeUserRepoModel
import org.junit.Assert.assertEquals
import org.junit.Test

class LocalMappersTests {

    @Test
    fun mapUserModel_passingModel_checkingFields() {
        // GIVEN
        val model = listOf(fakeUserModel)
        val expected = listOf(fakeUserEntity)
        // WHEN
        val actual = mapUserModelToEntity(model)
        // THEN
        assertEquals(expected, actual)
    }

    @Test
    fun mapUserRepoModel_passingModel_checkingFields() {
        // GIVEN
        val model = fakeUserRepoModel
        val expected = fakeUserRepoEntity
        // WHEN
        val actual = mapUserRepoModelToEntity(model)
        // THEN
        assertEquals(expected, actual)
    }
}