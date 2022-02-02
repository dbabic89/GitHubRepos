package com.dariobabic.githubrepos.features.user.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "COLUMN_ID")
    val id: Long = 0,

    @ColumnInfo(name = "COLUMN_AVATAR_URL")
    val avatarUrl: String,

    @ColumnInfo(name = "COLUMN_BIO")
    val bio: String? = null,

    @ColumnInfo(name = "COLUMN_BLOG")
    val blog: String? = null,

    @ColumnInfo(name = "COLUMN_LOGIN")
    val login: String,

    @ColumnInfo(name = "COLUMN_COMPANY")
    val company: String? = null,

    @ColumnInfo(name = "COLUMN_CREATED_AT")
    val createdAt: String,

    @ColumnInfo(name = "COLUMN_FOLLOWERS")
    val followers: Int = 0,

    @ColumnInfo(name = "COLUMN_FOLLOWING")
    val following: Int = 0,

    @ColumnInfo(name = "COLUMN_LOCATION")
    val location: String? = null,

    @ColumnInfo(name = "COLUMN_PUBLIC_REPOS_COUNT")
    val publicReposCount: Int = 0
)
