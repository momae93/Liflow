package com.example.liflow.di.components

import com.example.liflow.presentation.ui.login.LoginActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface LoginActivitySubComponent: AndroidInjector<LoginActivity> {
    @Subcomponent.Factory
    abstract class Factory: AndroidInjector.Factory<LoginActivity>
}