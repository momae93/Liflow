package com.example.liflow.di.builder

import com.example.liflow.di.scope.FragmentScope
import com.example.liflow.presentation.ui.post.fragment.DailyPostFragment
import com.example.liflow.presentation.ui.post.fragment.PostDetailsFragment
import com.example.liflow.presentation.ui.profile.fragment.ProfileFragment
import com.example.liflow.presentation.ui.profile.fragment.ProfilePostFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindProfileFragment(): ProfileFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindProfilePostFragment(): ProfilePostFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindPostDetailsFragment(): PostDetailsFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindDailyPostFragment(): DailyPostFragment
}