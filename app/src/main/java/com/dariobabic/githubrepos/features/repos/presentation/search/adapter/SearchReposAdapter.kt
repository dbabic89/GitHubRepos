package com.dariobabic.githubrepos.features.repos.presentation.search.adapter

import com.dariobabic.githubrepos.R
import com.dariobabic.githubrepos.core.components.adapters.CoreDataBoundAdapter
import com.dariobabic.githubrepos.core.components.adapters.CoreDataBoundViewHolder
import com.dariobabic.githubrepos.databinding.ItemSearchRepoBinding
import javax.inject.Inject

class SearchReposAdapter @Inject constructor() :
    CoreDataBoundAdapter<ItemSearchRepoBinding>() {

    private val repositories = mutableListOf<SearchRepoItem>()

    override fun bindItem(
        holder: CoreDataBoundViewHolder<ItemSearchRepoBinding>?,
        position: Int,
        payloads: List<Any?>?
    ) {
        holder?.binding?.data = repositories.getOrNull(position)
    }

    override fun getItemLayoutId(position: Int) = R.layout.item_search_repo

    override fun getItemCount() = repositories.size

    fun addRepositories(repositories: List<SearchRepoItem>) {
        this.repositories.clear()
        this.repositories.addAll(repositories)
        notifyItemRangeChanged(0, itemCount)
    }
}