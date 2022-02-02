package com.dariobabic.githubrepos.features.repos.presentation.details

import dagger.assisted.AssistedFactory

@AssistedFactory
interface RepoDetailsViewModelFactory {
    fun create(name: String): RepoDetailsViewModel
}