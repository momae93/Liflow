package com.example.liflow.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.liflow.domain.category.CategoryDomain
import com.example.liflow.domain.post.PostDomain
import com.example.liflow.domain.session.SessionDomain
import com.example.liflow.domain.user.UserDomain
import com.example.liflow.presentation.ui.category.viewmodel.CategoryDetailsViewModel
import com.example.liflow.presentation.ui.category.viewmodel.CategoryPostsViewModel
import com.example.liflow.presentation.ui.like.viewmodel.LikeCategoriesViewModel
import com.example.liflow.presentation.ui.like.viewmodel.LikePostsViewModel
import com.example.liflow.presentation.ui.like.viewmodel.LikeUsersViewModel
import com.example.liflow.presentation.ui.like.viewmodel.LikeViewModel
import com.example.liflow.presentation.ui.login.LoginViewModel
import com.example.liflow.presentation.ui.main.MainViewModel
import com.example.liflow.presentation.ui.post.viewmodel.DailyPostViewModel
import com.example.liflow.presentation.ui.post.viewmodel.PostDetailsViewModel
import com.example.liflow.presentation.ui.profile.viewmodel.ProfilePostViewModel
import com.example.liflow.presentation.ui.profile.viewmodel.CurrentProfileViewModel
import com.example.liflow.presentation.ui.profile.viewmodel.ProfileDetailsViewModel
import com.example.liflow.presentation.ui.search.viewmodel.SearchViewModel
import com.example.liflow.presentation.ui.splash.SplashViewModel
import javax.inject.Inject

class ViewModelProviderFactory: ViewModelProvider.NewInstanceFactory {
    private var userDomain: UserDomain
    private var postDomain: PostDomain
    private var sessionDomain: SessionDomain
    private var categoryDomain: CategoryDomain

    @Inject
    constructor(userDomain: UserDomain, postDomain: PostDomain, sessionDomain: SessionDomain, categoryDomain: CategoryDomain) {
        this.userDomain = userDomain
        this.postDomain = postDomain
        this.sessionDomain = sessionDomain
        this.categoryDomain = categoryDomain
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                SplashViewModel(userDomain) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(userDomain, sessionDomain) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(userDomain) as T
            }
            modelClass.isAssignableFrom(CurrentProfileViewModel::class.java) -> {
                CurrentProfileViewModel(userDomain, sessionDomain) as T
            }
            modelClass.isAssignableFrom(ProfilePostViewModel::class.java) -> {
                ProfilePostViewModel(userDomain) as T
            }
            modelClass.isAssignableFrom(PostDetailsViewModel::class.java) -> {
                PostDetailsViewModel(postDomain) as T
            }
            modelClass.isAssignableFrom(DailyPostViewModel::class.java) -> {
                DailyPostViewModel(postDomain) as T
            }
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                SearchViewModel(userDomain, categoryDomain) as T
            }
            modelClass.isAssignableFrom(CategoryDetailsViewModel::class.java) -> {
                CategoryDetailsViewModel(categoryDomain) as T
            }
            modelClass.isAssignableFrom(CategoryPostsViewModel::class.java) -> {
                CategoryPostsViewModel(categoryDomain) as T
            }
            modelClass.isAssignableFrom(LikeViewModel::class.java) -> {
                LikeViewModel() as T
            }
            modelClass.isAssignableFrom(LikeUsersViewModel::class.java) -> {
                LikeUsersViewModel(userDomain) as T
            }
            modelClass.isAssignableFrom(LikeCategoriesViewModel::class.java) -> {
                LikeCategoriesViewModel(userDomain) as T
            }
            modelClass.isAssignableFrom(LikePostsViewModel::class.java) -> {
                LikePostsViewModel(userDomain) as T
            }
            modelClass.isAssignableFrom(ProfileDetailsViewModel::class.java) -> {
                ProfileDetailsViewModel(userDomain) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}