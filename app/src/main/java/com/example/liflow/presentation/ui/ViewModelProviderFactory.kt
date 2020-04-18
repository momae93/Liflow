package com.example.liflow.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.liflow.domain.user.UserDomain
import com.example.liflow.presentation.ui.login.LoginViewModel
import com.example.liflow.presentation.ui.main.MainViewModel
import com.example.liflow.presentation.ui.profile.ProfileViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelProviderFactory: ViewModelProvider.NewInstanceFactory {
    private var userDomain: UserDomain

    @Inject
    constructor(userDomain: UserDomain) {
        this.userDomain = userDomain
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(userDomain) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(userDomain) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel() as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}