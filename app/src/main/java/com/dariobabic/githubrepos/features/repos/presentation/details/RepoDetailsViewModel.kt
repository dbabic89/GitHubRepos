package com.dariobabic.githubrepos.features.repos.presentation.details

import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.dariobabic.githubrepos.core.components.CoreViewModel
import com.dariobabic.githubrepos.core.constants.BASE_GITHUB_URL
import com.dariobabic.githubrepos.core.constants.EMPTY_STRING
import com.dariobabic.githubrepos.core.constants.SLASH
import com.dariobabic.githubrepos.core.utils.*
import com.dariobabic.githubrepos.features.repos.domain.entities.RepoEntity
import com.dariobabic.githubrepos.features.repos.domain.use_cases.LoadRepoUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlin.properties.Delegates

class RepoDetailsViewModel @AssistedInject constructor(
    loadRepoUseCase: LoadRepoUseCase,
    @Assisted val name: String
) : CoreViewModel() {

    lateinit var callback: RepoDetailsCallback

    @get:Bindable
    var language by Delegates.observable(EMPTY_STRING) { _, _, _ ->
        notifyPropertyChanged(BR.language)
    }

    @get:Bindable
    var description by Delegates.observable(EMPTY_STRING) { _, _, _ ->
        notifyPropertyChanged(BR.description)
    }

    @get:Bindable
    var createdAt by Delegates.observable(EMPTY_STRING) { _, _, _ ->
        notifyPropertyChanged(BR.createdAt)
    }

    @get:Bindable
    var updatedAt by Delegates.observable(EMPTY_STRING) { _, _, _ ->
        notifyPropertyChanged(BR.updatedAt)
    }

    @get:Bindable
    var ownerName by Delegates.observable(EMPTY_STRING) { _, _, _ ->
        notifyPropertyChanged(BR.ownerName)
    }

    @get:Bindable
    var ownerAvatarUrl by Delegates.observable(EMPTY_STRING) { _, _, _ ->
        notifyPropertyChanged(BR.ownerAvatarUrl)
    }

    @get:Bindable
    var watchers by Delegates.observable(EMPTY_STRING) { _, _, _ ->
        notifyPropertyChanged(BR.watchers)
    }

    @get:Bindable
    var watcherCount by Delegates.observable(0) { _, _, _ ->
        notifyPropertyChanged(BR.watcherCount)
    }

    @get:Bindable
    var forks by Delegates.observable(EMPTY_STRING) { _, _, _ ->
        notifyPropertyChanged(BR.forks)
    }

    @get:Bindable
    var forkCount by Delegates.observable(0) { _, _, _ ->
        notifyPropertyChanged(BR.forkCount)
    }

    @get:Bindable
    var issues by Delegates.observable(EMPTY_STRING) { _, _, _ ->
        notifyPropertyChanged(BR.issues)
    }

    @get:Bindable
    var issueCount by Delegates.observable(0) { _, _, _ ->
        notifyPropertyChanged(BR.issueCount)
    }

    init {
        loading = true
        loadRepoUseCase.setup(name)
            .execute(object : CoreSingleObserver<RepoEntity>() {
                override fun onSuccess(entity: RepoEntity) {
                    language = entity.language
                    description = entity.description ?: EMPTY_STRING

                    ownerName = entity.ownerName
                    ownerAvatarUrl = entity.ownerAvatarUrl

                    watchers = formatCounter(entity.watcherCount)
                    watcherCount = entity.watcherCount
                    forks = formatCounter(entity.forkCount)
                    forkCount = entity.forkCount
                    issues = formatCounter(entity.issueCount)
                    issueCount = entity.issueCount

                    createdAt = formatDate(entity.createdAt)
                    updatedAt = formatDate(entity.updatedAt)
                    loading = false
                }
            })
    }

    fun openRepoDetailsLink() {
        val link = "$BASE_GITHUB_URL${ownerName}$SLASH${name}"
        callback.openRepoLink(link)
    }

    fun openOwnerDetails() {
        callback.openOwner(ownerName)
    }
}