package com.dariobabic.githubrepos.features.repos.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dariobabic.githubrepos.features.repos.data.local.models.RepoModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface ReposDao {

    @Query("DELETE FROM RepoModel")
    fun removeRepos(): Completable

    @Insert(entity = RepoModel::class, onConflict = OnConflictStrategy.REPLACE)
    fun saveRepos(models: List<RepoModel>): Completable

    @Query("SELECT * FROM RepoModel")
    fun loadRepos(): Observable<List<RepoModel>>

    @Query("SELECT * FROM RepoModel WHERE COLUMN_NAME=:name")
    fun loadRepo(name: String): Single<RepoModel>
}