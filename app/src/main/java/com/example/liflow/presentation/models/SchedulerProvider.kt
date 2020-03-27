package com.example.liflow.presentation.models

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

import io.reactivex.rxjava3.schedulers.Schedulers


class SchedulerProvider {
    fun io(): Scheduler {
        return Schedulers.io()
    }

    fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}