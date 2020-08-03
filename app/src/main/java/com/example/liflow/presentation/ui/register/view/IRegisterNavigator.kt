package com.example.liflow.presentation.ui.register.view

import com.example.liflow.presentation.ui.base.IBaseNavigator

interface IRegisterNavigator: IBaseNavigator {
    fun navigateToMainActivity()
    fun openBirthDateDialog()
}