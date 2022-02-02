package com.dariobabic.githubrepos.features.owner.data

import com.dariobabic.githubrepos.features.owner.data.remote.mappers.mapOwnerResponseToModel
import com.dariobabic.githubrepos.features.owner.fakeOwnerModel
import com.dariobabic.githubrepos.features.owner.fakeOwnerResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class RemoteMappersTests {

    @Test
    fun mapOwnerResponse_passingResponse_checkingFields() {
        // GIVEN
        val response = fakeOwnerResponse
        val expected = fakeOwnerModel
        // WHEN
        val actual = mapOwnerResponseToModel(response)
        // THEN
        assertEquals(expected, actual)
    }
}