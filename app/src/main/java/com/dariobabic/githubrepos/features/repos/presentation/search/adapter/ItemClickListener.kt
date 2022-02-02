package com.dariobabic.githubrepos.features.repos.presentation.search.adapter

interface ItemClickListener {

    fun onRepoClicked(name: String)

    fun onOwnerClicked(ownerName: String)
}