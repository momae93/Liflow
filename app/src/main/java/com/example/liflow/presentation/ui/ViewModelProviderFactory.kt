package com.example.liflow.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.liflow.domain.user.UserDomain
import com.example.liflow.presentation.ui.login.LoginViewModel
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
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(userDomain) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}