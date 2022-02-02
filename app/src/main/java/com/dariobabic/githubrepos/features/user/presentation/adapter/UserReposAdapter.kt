package com.dariobabic.githubrepos.features.user.presentation.adapter

import com.dariobabic.githubrepos.R
import com.dariobabic.githubrepos.core.components.adapters.CoreDataBoundAdapter
import com.dariobabic.githubrepos.core.components.adapters.CoreDataBoundViewHolder
import com.dariobabic.githubrepos.databinding.ItemUserRepoBinding
import javax.inject.Inject

class UserReposAdapter @Inject constructor() :
    CoreDataBoundAdapter<ItemUserRepoBinding>() {

    private val repos = mutableListOf<UserRepoItem>()

    override fun bindItem(
        holder: CoreDataBoundViewHolder<ItemUserRepoBinding>?,
        position: Int,
        payloads: List<Any?>?
    ) {
        holder?.binding?.data = repos.getOrNull(position)
    }

    override fun getItemLayoutId(position: Int) = R.layout.item_user_repo

    override fun getItemCount() = repos.size

    fun addRepositories(repos: List<UserRepoItem>) {
        this.repos.clear()
        this.repos.addAll(repos)
        notifyItemRangeChanged(0, itemCount)
    }
}