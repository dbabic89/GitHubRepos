package com.dariobabic.githubrepos.core.components.use_cases

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class ObservableUseCase<Results> {

    abstract fun buildUseCaseObservable(): Observable<Results>

    fun execute(observer: Observer<Results>) = buildUseCaseWithSchedulers().subscribe(observer)

    fun execute(): Disposable = buildUseCaseWithSchedulers().subscribe()

    private fun buildUseCaseWithSchedulers(): Observable<Results> {
        return buildUseCaseObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}