package com.example.liflow.di.modules

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
}