package com.example.liflow.presentation.ui.like.fragment

import com.example.liflow.presentation.ui.base.IBaseNavigator

interface ILikeCategoriesNavigator: IBaseNavigator {
    fun navigateToCategoryDetailsFragment(categoryId: Int)
}