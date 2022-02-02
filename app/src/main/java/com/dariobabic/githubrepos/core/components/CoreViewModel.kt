package com.dariobabic.githubrepos.core.components

import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModel
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlin.properties.Delegates

open class CoreViewModel : ViewModel(), Observable {

    @Transient
    private var callbacks: PropertyChangeRegistry? = null

    private val compositeDisposable = CompositeDisposable()

    @get:Bindable
    var loading by Delegates.observable(false) { _, _, _ ->
        notifyPropertyChanged(BR.loading)
    }

    fun logError(e: Throwable) {
        Log.e(javaClass.simpleName, e.localizedMessage, e)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        synchronized(this) {
            if (callbacks == null) {
                callbacks = PropertyChangeRegistry()
            }
        }
        callbacks?.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        synchronized(this) {
            if (callbacks == null) return
        }
        callbacks?.remove(callback)
    }

    protected fun notifyPropertyChanged(fieldId: Int) {
        synchronized(this) {
            if (callbacks == null) return
        }
        callbacks?.notifyCallbacks(this, fieldId, null)
    }

    abstract inner class CoreObserver<T> : Observer<T> {
        override fun onSubscribe(d: Disposable) {
            compositeDisposable.add(d)
        }

        override fun onComplete() {}

        override fun onError(e: Throwable) {
            logError(e)
        }
    }

    abstract inner class CoreSingleObserver<T> : SingleObserver<T> {
        override fun onSubscribe(d: Disposable) {
            compositeDisposable.add(d)
        }

        override fun onError(e: Throwable) {
            logError(e)
        }
    }
}