package com.dariobabic.githubrepos.features.repos.presentation.search

interface SearchReposCallback {

    fun openRepo(name: String)

    fun openOwner(ownerName: String)
}