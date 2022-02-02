package com.dariobabic.githubrepos.features.repos.presentation.details

interface RepoDetailsCallback {

    fun openRepoLink(link: String)

    fun openOwner(ownerName: String)
}