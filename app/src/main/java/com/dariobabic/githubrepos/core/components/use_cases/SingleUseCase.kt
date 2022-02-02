package com.dariobabic.githubrepos.core.components.use_cases

import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<Results> {

    abstract fun buildUseCaseSingle(): Single<Results>

    fun execute(observer: SingleObserver<Results>) = buildUseCaseWithSchedulers().subscribe(observer)

    fun execute(): Disposable = buildUseCaseWithSchedulers().subscribe()

    private fun buildUseCaseWithSchedulers(): Single<Results> {
        return buildUseCaseSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}