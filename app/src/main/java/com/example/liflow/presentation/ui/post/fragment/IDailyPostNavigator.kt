package com.example.liflow.presentation.ui.post.fragment

import com.example.liflow.presentation.ui.base.IBaseNavigator

interface IDailyPostNavigator: IBaseNavigator {
    fun navigateToPostDetails(postDetails: Int)
}