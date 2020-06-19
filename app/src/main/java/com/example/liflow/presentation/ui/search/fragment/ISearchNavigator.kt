package com.example.liflow.presentation.ui.search.fragment

import com.example.liflow.presentation.ui.base.IBaseNavigator

interface ISearchNavigator: IBaseNavigator {
    fun navigateToUserDetails(userId: Int)
    fun navigateToCategoryDetails(userId: Int)
}