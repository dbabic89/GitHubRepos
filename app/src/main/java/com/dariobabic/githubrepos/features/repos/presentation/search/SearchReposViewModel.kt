package com.dariobabic.githubrepos.features.repos.presentation.search

import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.dariobabic.githubrepos.core.bindings.radio_group.RadioGroupChangeListener
import com.dariobabic.githubrepos.core.components.CoreViewModel
import com.dariobabic.githubrepos.core.constants.EMPTY_STRING
import com.dariobabic.githubrepos.features.repos.domain.entities.RepoEntity
import com.dariobabic.githubrepos.features.repos.domain.use_cases.ClearReposUseCase
import com.dariobabic.githubrepos.features.repos.domain.use_cases.GetSearchReposUseCase
import com.dariobabic.githubrepos.features.repos.presentation.search.adapter.ItemClickListener
import com.dariobabic.githubrepos.features.repos.presentation.search.adapter.SearchRepoItem
import com.dariobabic.githubrepos.features.repos.presentation.search.adapter.SearchReposAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class SearchReposViewModel @Inject constructor(
    private val getSearchReposUseCase: GetSearchReposUseCase,
    private val clearReposUseCase: ClearReposUseCase
) : CoreViewModel(), ItemClickListener {

    @Inject
    lateinit var adapterSearch: SearchReposAdapter
    lateinit var callback: SearchReposCallback

    private var searchQuerySubject = PublishSubject.create<String>()
    private var sortSubject = PublishSubject.create<String>()

    @get:Bindable
    var searchQuery by Delegates.observable(EMPTY_STRING) { _, _, newValue ->
        handleSearchQueryChange(newValue)
        notifyPropertyChanged(BR.searchQuery)
    }

    @get:Bindable
    var sortBy by Delegates.observable(EMPTY_STRING) { _, _, _ ->
        notifyPropertyChanged(BR.sortBy)
    }

    val radioGroupChangeListener = object : RadioGroupChangeListener {
        override fun onChange(label: String) {
            sortBy = label
            sortSubject.onNext(label)
        }
    }

    init {
        searchQuerySubject.debounce(1, TimeUnit.SECONDS)
            .filter { searchQuery.isNotEmpty() }
            .switchMap { search() }
            .subscribe(loadData())

        sortSubject
            .filter { searchQuery.isNotEmpty() }
            .switchMap { search() }
            .subscribe(loadData())
    }

    private fun search(): Observable<List<RepoEntity>> {
        loading = true
        return getSearchReposUseCase
            .setup(searchQuery, sortBy)
            .buildUseCaseObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun loadData(): CoreObserver<List<RepoEntity>> {
        return object : CoreObserver<List<RepoEntity>>() {
            override fun onNext(entities: List<RepoEntity>) {
                adapterSearch.addRepositories(
                    entities.map { SearchRepoItem(it, this@SearchReposViewModel) }
                )
                loading = false
            }
        }
    }

    private fun handleSearchQueryChange(query: String) {
        if (query.isEmpty()) {
            loading = false
            sortBy = EMPTY_STRING
            clearReposUseCase.execute()
        } else {
            searchQuerySubject.onNext(query)
        }
    }

    override fun onRepoClicked(name: String) = callback.openRepo(name)

    override fun onOwnerClicked(ownerName: String) = callback.openOwner(ownerName)
}