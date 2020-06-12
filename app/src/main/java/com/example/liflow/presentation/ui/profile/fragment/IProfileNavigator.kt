package com.example.liflow.presentation.ui.profile.fragment

import com.example.liflow.presentation.ui.base.IBaseNavigator

interface IProfileNavigator: IBaseNavigator {
    fun navigateToPostFragment(isLikedPostsCategory: Boolean)
    fun navigateToLoginActivity()
}