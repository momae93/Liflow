package com.example.liflow.presentation.ui.profile.fragment

import com.example.liflow.presentation.ui.base.IBaseNavigator

interface IProfileDetailsNavigator: IBaseNavigator {
    fun navigateToWrittenPostsFragment()
    fun navigateToBadgesFragment()
}