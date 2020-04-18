package com.example.liflow.di.builder

import com.example.liflow.presentation.ui.login.LoginActivity
import com.example.liflow.presentation.ui.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun bindProfileFragment(): ProfileFragment
}