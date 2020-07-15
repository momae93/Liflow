package com.example.liflow.presentation.ui.category.fragment

import com.example.liflow.presentation.ui.base.IBaseNavigator

interface ICategoryPostsNavigator: IBaseNavigator {
    fun navigateToCategoryDetailsFragment(postId: Int)
}