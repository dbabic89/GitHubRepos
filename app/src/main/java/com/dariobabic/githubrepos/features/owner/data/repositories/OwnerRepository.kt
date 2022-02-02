package com.dariobabic.githubrepos.features.owner.data.repositories

import com.dariobabic.githubrepos.features.owner.data.local.data_sources.LocalDataSourceContract
import com.dariobabic.githubrepos.features.owner.data.local.mappers.mapOwnerModelToEntity
import com.dariobabic.githubrepos.features.owner.data.local.mappers.mapOwnerRepoModelListToEntityList
import com.dariobabic.githubrepos.features.owner.data.remote.data_sources.RemoteDataSourceContract
import com.dariobabic.githubrepos.features.owner.data.remote.mappers.mapOwnerReposResponseListToModelList
import com.dariobabic.githubrepos.features.owner.data.remote.mappers.mapOwnerResponseToModel
import com.dariobabic.githubrepos.features.owner.domain.abstractions.OwnerRepositoryContract
import com.dariobabic.githubrepos.features.owner.domain.entities.OwnerEntity
import com.dariobabic.githubrepos.features.owner.domain.entities.OwnerRepoEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class OwnerRepository @Inject constructor(
    private val localDataSource: LocalDataSourceContract,
    private val remoteDataSource: RemoteDataSourceContract
) : OwnerRepositoryContract {

    override fun getOwnerDetails(ownerName: String): Completable {
        return fetchOwnerInfo(ownerName).concatWith(fetchOwnerRepos(ownerName))
    }

    override fun loadOwnerInfo(): Single<OwnerEntity> {
        return localDataSource.loadOwnerInfo()
            .map { mapOwnerModelToEntity(it) }
    }

    override fun loadOwnerRepos(): Observable<List<OwnerRepoEntity>> =
        localDataSource.removeOwnerRepos()
            .andThen(localDataSource.loadOwnerRepos()
                .map { mapOwnerRepoModelListToEntityList(it) }
            )

    private fun fetchOwnerInfo(ownerName: String): Completable {
        return remoteDataSource.getOwnerInfo(ownerName)
            .map { mapOwnerResponseToModel(it) }
            .flatMapCompletable { localDataSource.saveOwnerInfo(it) }
    }

    private fun fetchOwnerRepos(ownerName: String): Completable {
        return remoteDataSource.getOwnerRepos(ownerName)
            .map { mapOwnerReposResponseListToModelList(it) }
            .flatMapCompletable { localDataSource.saveOwnerRepos(it) }
    }
}