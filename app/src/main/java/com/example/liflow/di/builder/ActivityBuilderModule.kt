package com.example.liflow.di.builder

import com.example.liflow.presentation.ui.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): LoginActivity
}