package com.example.liflow.presentation.ui.main

import androidx.lifecycle.ViewModel
import com.example.liflow.domain.user.UserDomain
import javax.inject.Inject

class MainViewModel: ViewModel {
    private var userDomain: UserDomain

    @Inject
    constructor(userDomain: UserDomain) {
        this.userDomain = userDomain
    }
}