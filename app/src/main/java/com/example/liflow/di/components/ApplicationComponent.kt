package com.example.liflow.di.components

import com.example.liflow.di.builder.ActivityBuilderModule
import com.example.liflow.di.modules.ApplicationModule
import com.example.liflow.di.modules.DomainModule
import com.example.liflow.di.modules.RepositoryModule
import com.example.liflow.presentation.AndroidApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityBuilderModule::class,
    ApplicationModule::class,
    DomainModule::class,
    RepositoryModule::class
])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
}