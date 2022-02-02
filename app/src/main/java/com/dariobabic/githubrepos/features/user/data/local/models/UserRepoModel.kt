package com.dariobabic.githubrepos.features.user.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserRepoModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "COLUMN_ID")
    val id: Long = 0,

    @ColumnInfo(name = "COLUMN_NAME")
    val name: String,

    @ColumnInfo(name = "COLUMN_LANGUAGE")
    val language: String,

    @ColumnInfo(name = "COLUMN_WATCHER_COUNT")
    val watcherCount: Int,

    @ColumnInfo(name = "COLUMN_FORK_COUNT")
    val forkCount: Int,

    @ColumnInfo(name = "COLUMN_ISSUE_COUNT")
    val issueCount: Int
)

