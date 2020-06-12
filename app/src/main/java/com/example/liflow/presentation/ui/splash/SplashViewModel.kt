package com.example.liflow.presentation.ui.splash

import androidx.lifecycle.ViewModel
import com.example.liflow.domain.user.UserDomain
import javax.inject.Inject

class SplashViewModel : ViewModel {
    private var userDomain: UserDomain

    @Inject
    constructor(userDomain: UserDomain) {
        this.userDomain = userDomain
    }

    fun refreshSessionToken() {}
}

