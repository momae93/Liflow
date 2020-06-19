package com.example.liflow.presentation

import android.app.Application
import com.example.liflow.di.components.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class AndroidApplication: Application(), HasAndroidInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>

    @Override
    override fun onCreate() {
        super.onCreate()
        initializeInjector()
    }

    private fun initializeInjector() {
          DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return activityInjector
    }
}