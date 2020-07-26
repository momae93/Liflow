package com.example.liflow.presentation.ui.profile.fragment

import com.example.liflow.presentation.ui.base.IBaseNavigator

interface IProfileNavigator: IBaseNavigator {
    fun navigateToWrittenPostsFragment()
    fun navigateToBadgesFragment()
    fun navigateToLoginActivity()
}