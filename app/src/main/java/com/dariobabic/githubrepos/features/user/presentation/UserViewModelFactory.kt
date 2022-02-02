package com.dariobabic.githubrepos.features.user.presentation

import dagger.assisted.AssistedFactory

@AssistedFactory
interface UserViewModelFactory {
    fun create(name: String, isOwner: Boolean): UserViewModel
}