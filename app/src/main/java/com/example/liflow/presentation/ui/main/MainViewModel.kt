package com.example.liflow.presentation.ui.main

import androidx.lifecycle.ViewModel
import com.example.liflow.domain.user.UserDomain
import com.example.liflow.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel: BaseViewModel<IMainNavigator> {
    private var userDomain: UserDomain

    @Inject
    constructor(userDomain: UserDomain) {
        this.userDomain = userDomain
    }
}