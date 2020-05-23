package com.example.liflow.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.liflow.presentation.models.SchedulerProvider
import com.example.liflow.presentation.ui.ViewModelProviderFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return SchedulerProvider()
    }

    @Provides
    fun provideSharePreferences(application: Application): SharedPreferences {
        val filename = "${application.packageName}_preferences"
        return application.getSharedPreferences(filename, Context.MODE_PRIVATE)
    }
}