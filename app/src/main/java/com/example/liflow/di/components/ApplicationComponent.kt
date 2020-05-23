package com.example.liflow.di.components

import android.app.Application
import com.example.liflow.di.builder.ActivityBuilderModule
import com.example.liflow.di.builder.FragmentBuilderModule
import com.example.liflow.di.modules.ApplicationModule
import com.example.liflow.di.modules.DomainModule
import com.example.liflow.di.modules.RepositoryModule
import com.example.liflow.di.modules.SessionModule
import com.example.liflow.presentation.AndroidApplication
import dagger.BindsInstance
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
    RepositoryModule::class,
    SessionModule::class
])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}