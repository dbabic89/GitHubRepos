package com.dariobabic.githubrepos.features.user.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dariobabic.githubrepos.features.user.data.local.models.UserModel
import com.dariobabic.githubrepos.features.user.data.local.models.UserRepoModel
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface UserDao {

    @Query("DELETE FROM UserModel")
    fun removeUser(): Completable

    @Insert(entity = UserModel::class, onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(model: UserModel): Completable

    @Query("SELECT * FROM UserModel")
    fun loadUser(): Observable<List<UserModel>>

    @Query("DELETE FROM UserRepoModel")
    fun removeUserRepos(): Completable

    @Insert(entity = UserRepoModel::class, onConflict = OnConflictStrategy.REPLACE)
    fun saveUserRepos(models: List<UserRepoModel>): Completable

    @Query("SELECT * FROM UserRepoModel")
    fun loadUserRepos(): Observable<List<UserRepoModel>>
}