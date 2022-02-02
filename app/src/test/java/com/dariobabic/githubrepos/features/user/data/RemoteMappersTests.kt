package com.dariobabic.githubrepos.features.user.data

import com.dariobabic.githubrepos.features.user.data.remote.mappers.mapUserResponseToModel
import com.dariobabic.githubrepos.features.user.fakeUserModel
import com.dariobabic.githubrepos.features.user.fakeUserResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class RemoteMappersTests {

    @Test
    fun mapUserResponse_passingResponse_checkingFields() {
        // GIVEN
        val response = fakeUserResponse
        val expected = fakeUserModel
        // WHEN
        val actual = mapUserResponseToModel(response)
        // THEN
        assertEquals(expected, actual)
    }
}