package com.dariobabic.githubrepos.features.user.presentation

import com.dariobabic.githubrepos.features.owner.fakeOwnerEntity
import com.dariobabic.githubrepos.features.owner.fakeOwnerRepoEntity
import com.dariobabic.githubrepos.features.user.fakeInfoModel
import com.dariobabic.githubrepos.features.user.fakeRepoModel
import com.dariobabic.githubrepos.features.user.fakeUserEntity
import com.dariobabic.githubrepos.features.user.fakeUserRepoEntity
import com.dariobabic.githubrepos.features.user.presentation.mappers.mapOwnerEntityListToInfoModelList
import com.dariobabic.githubrepos.features.user.presentation.mappers.mapOwnerRepoEntityListToModelList
import com.dariobabic.githubrepos.features.user.presentation.mappers.mapUserEntityListToInfoModelList
import com.dariobabic.githubrepos.features.user.presentation.mappers.mapUserRepoEntityListToModelList
import org.junit.Assert
import org.junit.Test

class PresentationMappers {

    @Test
    fun mapUserEntityList_passingUserEntityList_returnsInfoModel() {
        // GIVEN
        val entity = listOf(fakeUserEntity)
        val expected = listOf(fakeInfoModel)
        // WHEN
        val actual = mapUserEntityListToInfoModelList(entity)
        // THEN
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun mapOwnerEntityList_passingOwnerEntityList_returnsInfoModel() {
        // GIVEN
        val entity = listOf(fakeOwnerEntity)
        val expected = listOf(fakeInfoModel)
        // WHEN
        val actual = mapOwnerEntityListToInfoModelList(entity)
        // THEN
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun mapUserRepoEntityList_passingUserRepoEntityList_returnsRepoModel() {
        // GIVEN
        val entity = listOf(fakeUserRepoEntity)
        val expected = listOf(fakeRepoModel)
        // WHEN
        val actual = mapUserRepoEntityListToModelList(entity)
        // THEN
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun mapOwnerRepoEntityList_passingOwnerRepoEntityList_returnsRepoModel() {
        // GIVEN
        val entity = listOf(fakeOwnerRepoEntity)
        val expected = listOf(fakeRepoModel)
        // WHEN
        val actual = mapOwnerRepoEntityListToModelList(entity)
        // THEN
        Assert.assertEquals(expected, actual)
    }
}