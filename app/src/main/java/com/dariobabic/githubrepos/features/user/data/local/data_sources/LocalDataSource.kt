package com.dariobabic.githubrepos.features.user.data.local.data_sources

import com.dariobabic.githubrepos.features.user.data.local.daos.UserDao
import com.dariobabic.githubrepos.features.user.data.local.models.UserModel
import com.dariobabic.githubrepos.features.user.data.local.models.UserRepoModel
import io.reactivex.Completable
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: UserDao
) : LocalDataSourceContract {

    override fun loadUserInfo() = dao.loadUser()

    override fun saveUserInfo(model: UserModel): Completable {
        return dao.removeUser()
            .andThen(dao.saveUser(model))
    }

    override fun loadUserRepos() = dao.loadUserRepos()

    override fun saveUserRepos(models: List<UserRepoModel>): Completable {
        return dao.removeUserRepos()
            .andThen(dao.saveUserRepos(models))
    }
}