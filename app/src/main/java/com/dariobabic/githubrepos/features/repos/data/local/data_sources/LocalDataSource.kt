package com.dariobabic.githubrepos.features.repos.data.local.data_sources

import com.dariobabic.githubrepos.features.repos.data.local.daos.ReposDao
import com.dariobabic.githubrepos.features.repos.data.local.models.RepoModel
import io.reactivex.Completable
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: ReposDao
) : LocalDataSourceContract {

    override fun clearRepos() = dao.removeRepos()

    override fun saveRepos(models: List<RepoModel>): Completable {
        return dao.removeRepos()
            .andThen(dao.saveRepos(models))
    }

    override fun loadRepos() = dao.loadRepos()

    override fun loadRepo(name: String) = dao.loadRepo(name)
}