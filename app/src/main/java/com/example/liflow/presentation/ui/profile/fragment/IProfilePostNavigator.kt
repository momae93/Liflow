package com.example.liflow.presentation.ui.profile.fragment

import com.example.liflow.presentation.ui.base.IBaseNavigator

interface IProfilePostNavigator: IBaseNavigator {
    fun navigateToPostFragment(postId: Int)
}