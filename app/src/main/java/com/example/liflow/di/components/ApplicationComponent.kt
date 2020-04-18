package com.example.liflow.di.components

import com.example.liflow.di.builder.ActivityBuilderModule
import com.example.liflow.di.builder.FragmentBuilderModule
import com.example.liflow.di.modules.*
import com.example.liflow.presentation.AndroidApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ApplicationModule::class,
    ActivityBuilderModule::class,
    FragmentBuilderModule::class,
    DomainModule::class,
    RepositoryModule::class
])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
}