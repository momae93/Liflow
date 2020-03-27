package com.example.liflow.presentation

import android.app.Activity
import android.app.Application
import com.example.liflow.di.components.ApplicationComponent
import com.example.liflow.di.components.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class AndroidApplication: Application(), HasAndroidInjector {
    private lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>

    @Override
    override fun onCreate() {
        super.onCreate()
        initializeInjector()
    }

    private fun initializeInjector() {
         applicationComponent = DaggerApplicationComponent
            .builder()
            .build()

        applicationComponent.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return this.applicationComponent
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return activityInjector
    }
}