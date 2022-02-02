package com.dariobabic.githubrepos.features.repos.presentation.search.adapter

import androidx.databinding.BaseObservable
import com.dariobabic.githubrepos.core.utils.formatCounter
import com.dariobabic.githubrepos.features.repos.domain.entities.RepoEntity

class SearchRepoItem(
    val entity: RepoEntity,
    private val clickListener: ItemClickListener
) : BaseObservable() {

    val watcherCount = formatCounter(entity.watcherCount)
    val forkCount = formatCounter(entity.forkCount)
    val issueCount = formatCounter(entity.issueCount)

    fun openRepoDetails() {
        clickListener.onRepoClicked(entity.name)
    }

    fun openOwnerDetails() {
        clickListener.onOwnerClicked(entity.ownerName)
    }
}