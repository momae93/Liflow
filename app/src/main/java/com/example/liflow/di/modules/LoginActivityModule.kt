package com.example.liflow.di.modules

import com.example.liflow.di.components.LoginActivitySubComponent
import com.example.liflow.presentation.ui.login.LoginActivity
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [LoginActivitySubComponent::class])
abstract class LoginActivityModule {
    @Binds
    @IntoMap
    @ClassKey(LoginActivity::class)
    abstract fun bindLoginActivity(loginActivitySubComponent: LoginActivitySubComponent.Factory): AndroidInjector.Factory<*>
}