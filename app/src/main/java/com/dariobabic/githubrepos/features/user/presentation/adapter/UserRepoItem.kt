package com.dariobabic.githubrepos.features.user.presentation.adapter

import androidx.databinding.BaseObservable
import com.dariobabic.githubrepos.core.utils.formatCounter
import com.dariobabic.githubrepos.features.user.presentation.models.RepoModel

class UserRepoItem(
    val model: RepoModel
) : BaseObservable() {

    val watcherCount = formatCounter(model.watcherCount)
    val forkCount = formatCounter(model.forkCount)
    val issueCount = formatCounter(model.issueCount)
}