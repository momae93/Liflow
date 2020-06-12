package com.example.liflow.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.liflow.di.scope.ActivityScope
import com.example.liflow.domain.post.PostDomain
import com.example.liflow.domain.session.SessionDomain
import com.example.liflow.domain.user.UserDomain
import com.example.liflow.presentation.ui.login.LoginViewModel
import com.example.liflow.presentation.ui.main.MainViewModel
import com.example.liflow.presentation.ui.post.viewmodel.DailyPostViewModel
import com.example.liflow.presentation.ui.post.viewmodel.PostDetailsViewModel
import com.example.liflow.presentation.ui.profile.viewmodel.ProfilePostViewModel
import com.example.liflow.presentation.ui.profile.viewmodel.ProfileViewModel
import com.example.liflow.presentation.ui.splash.SplashViewModel
import javax.inject.Inject

class ViewModelProviderFactory: ViewModelProvider.NewInstanceFactory {
    private var userDomain: UserDomain
    private var postDomain: PostDomain
    private var sessionDomain: SessionDomain

    @Inject
    constructor(userDomain: UserDomain, postDomain: PostDomain, sessionDomain: SessionDomain) {
        this.userDomain = userDomain
        this.postDomain = postDomain
        this.sessionDomain = sessionDomain
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
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(userDomain, sessionDomain) as T
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
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}