package com.example.liflow.presentation.ui.category.fragment

import com.example.liflow.presentation.ui.base.IBaseNavigator

interface ICategoryDetailsNavigator: IBaseNavigator {
    fun navigateToCategoryPostsFragment()
    fun navigateToProfileDetailsFragment(userId: Int)
}