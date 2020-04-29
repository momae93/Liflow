package com.example.liflow.di.builder

import com.example.liflow.presentation.ui.profile.fragment.ProfileFragment
import com.example.liflow.presentation.ui.profile.fragment.ProfilePostFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun bindProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun bindProfilePostFragment(): ProfilePostFragment
}