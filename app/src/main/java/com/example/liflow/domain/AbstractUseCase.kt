package com.example.liflow.domain

import com.example.liflow.presentation.models.SchedulerProvider
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver

abstract class AbstractUseCase<RESPONSE, PARAMS>(private val schedulerProvider: SchedulerProvider) {
    private val disposables: CompositeDisposable = CompositeDisposable()

    internal abstract fun buildUseCaseObservable(params: PARAMS): Observable<RESPONSE>

    fun execute(observer: DisposableObserver<RESPONSE>, params: PARAMS){
        val observable = this.buildUseCaseObservable(params)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())

        this.addDisposable(observable.subscribeWith(observer))
    }

    fun dispose(){
        if (!disposables.isDisposed)
            disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}