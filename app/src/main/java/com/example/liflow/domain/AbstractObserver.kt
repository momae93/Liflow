package com.example.liflow.domain

import io.reactivex.rxjava3.observers.DisposableObserver

open class AbstractObserver<T>: DisposableObserver<T>() {
    override fun onComplete() {
    }

    override fun onNext(responseData: T) {
    }

    override fun onError(e: Throwable) {
    }
}