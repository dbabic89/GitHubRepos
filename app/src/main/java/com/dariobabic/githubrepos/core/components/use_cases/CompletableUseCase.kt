package com.dariobabic.githubrepos.core.components.use_cases

import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase {

    abstract fun buildUseCaseCompletable(): Completable

    fun execute(observer: CompletableObserver) = buildUseCaseWithSchedulers().subscribe(observer)

    fun execute(): Disposable = buildUseCaseWithSchedulers().subscribe()

    private fun buildUseCaseWithSchedulers(): Completable {
        return buildUseCaseCompletable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
