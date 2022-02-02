package com.dariobabic.githubrepos.features.owner.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dariobabic.githubrepos.features.owner.data.local.models.OwnerModel
import com.dariobabic.githubrepos.features.owner.data.local.models.OwnerRepoModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface OwnerDao {

    @Query("DELETE FROM OwnerModel")
    fun removeOwner(): Completable

    @Insert(entity = OwnerModel::class, onConflict = OnConflictStrategy.REPLACE)
    fun saveOwner(model: OwnerModel): Completable

    @Query("SELECT * FROM OwnerModel")
    fun loadOwner(): Single<OwnerModel>

    @Query("DELETE FROM OwnerRepoModel")
    fun removeOwnerRepos(): Completable

    @Insert(entity = OwnerRepoModel::class, onConflict = OnConflictStrategy.REPLACE)
    fun saveOwnerRepos(models: List<OwnerRepoModel>): Completable

    @Query("SELECT * FROM OwnerRepoModel")
    fun loadOwnerRepos(): Observable<List<OwnerRepoModel>>
}