package com.example.liflow.di.builder

import com.example.liflow.presentation.ui.post.fragment.DailyPostFragment
import com.example.liflow.presentation.ui.post.fragment.PostDetailsFragment
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

    @ContributesAndroidInjector
    abstract fun bindPostDetailsFragment(): PostDetailsFragment

    @ContributesAndroidInjector
    abstract fun bindDailyPostFragment(): DailyPostFragment
}