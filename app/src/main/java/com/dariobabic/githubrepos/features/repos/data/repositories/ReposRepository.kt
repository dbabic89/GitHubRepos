package com.dariobabic.githubrepos.features.repos.data.repositories

import com.dariobabic.githubrepos.features.repos.data.local.data_sources.LocalDataSourceContract
import com.dariobabic.githubrepos.features.repos.data.local.mappers.mapRepoModelListToEntityList
import com.dariobabic.githubrepos.features.repos.data.local.mappers.mapRepoModelToEntity
import com.dariobabic.githubrepos.features.repos.data.local.models.RepoModel
import com.dariobabic.githubrepos.features.repos.data.remote.data_sources.RemoteDataSourceContract
import com.dariobabic.githubrepos.features.repos.data.remote.mappers.mapSearchReposResponseToModel
import com.dariobabic.githubrepos.features.repos.domain.abstractions.ReposRepositoryContract
import com.dariobabic.githubrepos.features.repos.domain.entities.RepoEntity
import io.reactivex.Observable
import javax.inject.Inject

class ReposRepository @Inject constructor(
    private val localDataSource: LocalDataSourceContract,
    private val remoteDataSource: RemoteDataSourceContract
) : ReposRepositoryContract {

    override fun clearRepos() = localDataSource.clearRepos()

    override fun getSearchedRepos(query: String): Observable<List<RepoEntity>> {
        return remoteDataSource.getSearchedRepos(query)
            .map { mapSearchReposResponseToModel(it) }
            .flatMap { updateAndLoadGitRepos(it) }
    }

    override fun loadRepos(): Observable<List<RepoEntity>> {
        return localDataSource.loadRepos()
            .map { mapRepoModelListToEntityList(it) }
    }

    override fun loadRepo(name: String) =
        localDataSource.loadRepo(name)
            .map { mapRepoModelToEntity(it) }

    private fun updateAndLoadGitRepos(models: List<RepoModel>) =
        localDataSource.saveRepos(models)
            .andThen(localDataSource.loadRepos())
            .map { mapRepoModelListToEntityList(it) }
}