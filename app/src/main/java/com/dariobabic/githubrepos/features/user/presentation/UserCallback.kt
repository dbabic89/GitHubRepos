package com.dariobabic.githubrepos.features.user.presentation

interface UserCallback {

    fun startAuthorization()

    fun logout()

    fun onLinkClicked(link: String)
}