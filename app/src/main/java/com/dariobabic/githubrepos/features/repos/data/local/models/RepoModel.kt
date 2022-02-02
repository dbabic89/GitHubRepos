package com.dariobabic.githubrepos.features.repos.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RepoModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "COLUMN_ID")
    val id: Long = 0,

    @ColumnInfo(name = "COLUMN_NAME")
    val name: String,

    @ColumnInfo(name = "COLUMN_LANGUAGE")
    val language: String,

    @ColumnInfo(name = "COLUMN_DESCRIPTION")
    val description: String?,

    @ColumnInfo(name = "COLUMN_OWNER_NAME")
    val ownerName: String,

    @ColumnInfo(name = "COLUMN_OWNER_AVATAR_URL")
    val ownerAvatarUrl: String,

    @ColumnInfo(name = "COLUMN_CREATED_AT")
    val createdAt: String,

    @ColumnInfo(name = "COLUMN_UPDATED_AT")
    val updatedAt: String,

    @ColumnInfo(name = "COLUMN_WATCHER_COUNT")
    val watcherCount: Int,

    @ColumnInfo(name = "COLUMN_FORK_COUNT")
    val forkCount: Int,

    @ColumnInfo(name = "COLUMN_ISSUE_COUNT")
    val issueCount: Int
)
