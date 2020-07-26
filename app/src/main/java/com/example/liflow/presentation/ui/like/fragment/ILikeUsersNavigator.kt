package com.example.liflow.presentation.ui.like.fragment

import com.example.liflow.presentation.ui.base.IBaseNavigator

interface ILikeUsersNavigator: IBaseNavigator {
    fun navigateToUsersDetailsFragment(userId: Int)
}