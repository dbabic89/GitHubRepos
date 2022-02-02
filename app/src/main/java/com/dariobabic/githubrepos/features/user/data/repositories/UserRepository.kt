package com.dariobabic.githubrepos.features.user.data.repositories

import com.dariobabic.githubrepos.features.user.data.local.data_sources.LocalDataSourceContract
import com.dariobabic.githubrepos.features.user.data.local.mappers.mapUserModelToEntity
import com.dariobabic.githubrepos.features.user.data.local.mappers.mapUserRepoModelListToEntityList
import com.dariobabic.githubrepos.features.user.data.remote.data_sources.RemoteDataSourceContract
import com.dariobabic.githubrepos.features.user.data.remote.mappers.mapUserReposResponseListToModelList
import com.dariobabic.githubrepos.features.user.data.remote.mappers.mapUserResponseToModel
import com.dariobabic.githubrepos.features.user.domain.abstractions.UserRepositoryContract
import com.dariobabic.githubrepos.features.user.domain.entities.UserEntity
import com.dariobabic.githubrepos.features.user.domain.entities.UserRepoEntity
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val localDataSource: LocalDataSourceContract,
    private val remoteDataSource: RemoteDataSourceContract
) : UserRepositoryContract {

    override fun getUserDetails(accessToken: String): Completable {
        return fetchUserInfo(accessToken).concatWith(fetchUserRepos())
    }

    override fun loadUserInfo(): Observable<List<UserEntity>> {
        return localDataSource.loadUserInfo()
            .map { mapUserModelToEntity(it) }
    }

    override fun loadUserRepos(): Observable<List<UserRepoEntity>> {
        return localDataSource.loadUserRepos()
            .map { mapUserRepoModelListToEntityList(it) }
    }

    private fun fetchUserInfo(accessToken: String): Completable {
        return remoteDataSource.getUserInfo(accessToken)
            .map { mapUserResponseToModel(it) }
            .flatMapCompletable { localDataSource.saveUserInfo(it) }
    }

    private fun fetchUserRepos(): Completable {
        return localDataSource.loadUserInfo()
            .filter { !it.isNullOrEmpty() }
            .map { it[0].login }
            .flatMapSingle { remoteDataSource.getUserRepos(it) }
            .map { mapUserReposResponseListToModelList(it) }
            .flatMapCompletable { localDataSource.saveUserRepos(it) }
    }
}

