package com.example.liflow.di.builder

import com.example.liflow.di.scope.FragmentScope
import com.example.liflow.presentation.ui.category.fragment.CategoryDetailsFragment
import com.example.liflow.presentation.ui.category.fragment.CategoryPostsFragment
import com.example.liflow.presentation.ui.like.fragment.LikeCategoriesFragment
import com.example.liflow.presentation.ui.like.fragment.LikeFragment
import com.example.liflow.presentation.ui.like.fragment.LikePostsFragment
import com.example.liflow.presentation.ui.like.fragment.LikeUsersFragment
import com.example.liflow.presentation.ui.like.viewmodel.LikeUsersViewModel
import com.example.liflow.presentation.ui.post.fragment.DailyPostFragment
import com.example.liflow.presentation.ui.post.fragment.PostDetailsFragment
import com.example.liflow.presentation.ui.profile.fragment.ProfileFragment
import com.example.liflow.presentation.ui.profile.fragment.ProfilePostFragment
import com.example.liflow.presentation.ui.search.fragment.SearchFragment
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

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindSearchFragment(): SearchFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindCategoryDetailsFragment(): CategoryDetailsFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindCategoryPostsFragment(): CategoryPostsFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindLikeFragment(): LikeFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindLikeUsersFragment(): LikeUsersFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindLikeCategoriesFragment(): LikeCategoriesFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindLikePostsFragment(): LikePostsFragment
}