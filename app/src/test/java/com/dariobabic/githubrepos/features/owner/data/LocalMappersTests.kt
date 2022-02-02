package com.dariobabic.githubrepos.features.owner.data

import com.dariobabic.githubrepos.features.owner.data.local.mappers.mapOwnerModelToEntity
import com.dariobabic.githubrepos.features.owner.data.local.mappers.mapOwnerRepoModelToEntity
import com.dariobabic.githubrepos.features.owner.fakeOwnerEntity
import com.dariobabic.githubrepos.features.owner.fakeOwnerModel
import com.dariobabic.githubrepos.features.owner.fakeOwnerRepoEntity
import com.dariobabic.githubrepos.features.owner.fakeOwnerRepoModel
import org.junit.Assert.assertEquals
import org.junit.Test

class LocalMappersTests {

    @Test
    fun mapOwnerModel_passingModel_checkingFields() {
        // GIVEN
        val model = fakeOwnerModel
        val expected = fakeOwnerEntity
        // WHEN
        val actual = mapOwnerModelToEntity(model)
        // THEN
        assertEquals(expected, actual)
    }

    @Test
    fun mapOwnerRepoModel_passingModel_checkingFields() {
        // GIVEN
        val model = fakeOwnerRepoModel
        val expected = fakeOwnerRepoEntity
        // WHEN
        val actual = mapOwnerRepoModelToEntity(model)
        // THEN
        assertEquals(expected, actual)
    }
}