package com.dariobabic.githubrepos.features.user.presentation

import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.dariobabic.githubrepos.core.components.CoreViewModel
import com.dariobabic.githubrepos.core.constants.BASE_GITHUB_URL
import com.dariobabic.githubrepos.core.constants.EMPTY_STRING
import com.dariobabic.githubrepos.core.utils.formatCounter
import com.dariobabic.githubrepos.core.utils.formatDate
import com.dariobabic.githubrepos.features.user.presentation.adapter.UserRepoItem
import com.dariobabic.githubrepos.features.user.presentation.adapter.UserReposAdapter
import com.dariobabic.githubrepos.features.user.presentation.models.InfoModel
import com.dariobabic.githubrepos.features.user.presentation.models.RepoModel
import com.dariobabic.githubrepos.features.user.presentation.use_case_factory.UserUseCaseFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.properties.Delegates

class UserViewModel @AssistedInject constructor(
    userUseCaseFactory: UserUseCaseFactory,
    @Assisted val name: String,
    @Assisted val isOwner: Boolean
) : CoreViewModel() {

    @Inject
    lateinit var adapter: UserReposAdapter
    lateinit var callback: UserCallback

    @get:Bindable
    var displayInfo by Delegates.observable(false) { _, _, _ ->
        notifyPropertyChanged(BR.displayInfo)
    }

    @get:Bindable
    var loginVisible by Delegates.observable(false) { _, _, _ ->
        notifyPropertyChanged(BR.loginVisible)
    }

    @get:Bindable
    var logoutVisible by Delegates.observable(false) { _, _, _ ->
        notifyPropertyChanged(BR.logoutVisible)
    }

    @get:Bindable
    var avatarUrl by Delegates.observable(EMPTY_STRING) { _, _, _ ->
        notifyPropertyChanged(BR.avatarUrl)
    }

    @get:Bindable
    var bio by Delegates.observable(EMPTY_STRING) { _, _, _ ->
        notifyPropertyChanged(BR.bio)
    }

    @get:Bindable
    var blog by Delegates.observable(EMPTY_STRING) { _, _, _ ->
        notifyPropertyChanged(BR.blog)
    }

    @get:Bindable
    var login by Delegates.observable(EMPTY_STRING) { _, _, _ ->
        notifyPropertyChanged(BR.login)
    }

    @get:Bindable
    var company by Delegates.observable(EMPTY_STRING) { _, _, _ ->
        notifyPropertyChanged(BR.company)
    }

    @get:Bindable
    var createdAt by Delegates.observable(EMPTY_STRING) { _, _, _ ->
        notifyPropertyChanged(BR.createdAt)
    }

    @get:Bindable
    var followers by Delegates.observable(EMPTY_STRING) { _, _, _ ->
        notifyPropertyChanged(BR.followers)
    }

    @get:Bindable
    var followersCount by Delegates.observable(0) { _, _, _ ->
        notifyPropertyChanged(BR.followersCount)
    }

    @get:Bindable
    var following by Delegates.observable(EMPTY_STRING) { _, _, _ ->
        notifyPropertyChanged(BR.following)
    }

    @get:Bindable
    var location by Delegates.observable(EMPTY_STRING) { _, _, _ ->
        notifyPropertyChanged(BR.location)
    }

    @get:Bindable
    var publicReposCount by Delegates.observable(0) { _, _, _ ->
        notifyPropertyChanged(BR.publicReposCount)
    }

    init {
        loading = true
        val useCase = userUseCaseFactory.getUseCase(name, isOwner)
        useCase.getInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CoreObserver<List<InfoModel>>() {
                override fun onNext(entities: List<InfoModel>) {
                    setUi(entities)
                }
            })

        useCase.getRepos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CoreObserver<List<RepoModel>>() {
                override fun onNext(entities: List<RepoModel>) {
                    adapter.addRepositories(
                        entities.map { UserRepoItem(it) }
                    )
                }
            })
    }

    fun login() {
        callback.startAuthorization()
        loading = true
    }

    fun logout() = callback.logout()

    fun openDetailsLink() {
        val link = "$BASE_GITHUB_URL${login}"
        callback.onLinkClicked(link)
    }

    fun openBlogLink() {
        callback.onLinkClicked(blog)
    }

    private fun setUi(entities: List<InfoModel>) {
        if (entities.isNotEmpty()) {
            val entity = entities[0]
            avatarUrl = entity.avatarUrl
            bio = entity.bio
            blog = entity.blog
            login = entity.login
            company = entity.company
            createdAt = formatDate(entity.createdAt)
            followers = formatCounter(entity.followers)
            followersCount = entity.followers
            following = formatCounter(entity.following)
            location = entity.location
            publicReposCount = entity.publicReposCount
        }
        loading = false

        if (isOwner) {
            displayInfo = true
            loginVisible = false
            logoutVisible = false
        } else {
            displayInfo = entities.isNotEmpty()
            loginVisible = !displayInfo
            logoutVisible = displayInfo
        }
    }
}