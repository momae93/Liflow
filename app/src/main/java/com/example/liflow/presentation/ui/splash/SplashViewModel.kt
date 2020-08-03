package com.example.liflow.presentation.ui.splash

import androidx.lifecycle.ViewModel
import com.example.liflow.domain.user.UserDomain
import com.example.liflow.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class SplashViewModel : BaseViewModel<ISplashNavigator> {
    private var userDomain: UserDomain

    @Inject
    constructor(userDomain: UserDomain) {
        this.userDomain = userDomain
    }

    fun refreshSessionToken() {}
}

