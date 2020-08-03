package com.example.liflow.di.builder

import com.example.liflow.di.modules.SessionModule
import com.example.liflow.di.scope.ActivityScope
import com.example.liflow.presentation.ui.login.LoginActivity
import com.example.liflow.presentation.ui.main.MainActivity
import com.example.liflow.presentation.ui.register.view.RegisterActivity
import com.example.liflow.presentation.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [SessionModule::class])
abstract class ActivityBuilderModule {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): LoginActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindRegisterActivity(): RegisterActivity
}