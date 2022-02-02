package com.dariobabic.githubrepos.features.repos.data.remote.models

data class SearchReposResponse(
    val incomplete_results: Boolean = false,
    val items: List<RepoResponse?>? = null,
    val total_count: Int = 0)