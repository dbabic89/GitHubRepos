package com.dariobabic.githubrepos.features.owner.data.local.data_sources

import com.dariobabic.githubrepos.features.owner.data.local.daos.OwnerDao
import com.dariobabic.githubrepos.features.owner.data.local.models.OwnerModel
import com.dariobabic.githubrepos.features.owner.data.local.models.OwnerRepoModel
import io.reactivex.Completable
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: OwnerDao
) : LocalDataSourceContract {

    override fun loadOwnerInfo() = dao.loadOwner()

    override fun saveOwnerInfo(model: OwnerModel): Completable {
        return dao.removeOwner()
            .andThen(dao.saveOwner(model))
    }

    override fun loadOwnerRepos() = dao.loadOwnerRepos()

    override fun saveOwnerRepos(models: List<OwnerRepoModel>): Completable {
        return dao.removeOwnerRepos()
            .andThen(dao.saveOwnerRepos(models))
    }

    override fun removeOwnerRepos() = dao.removeOwnerRepos()
}