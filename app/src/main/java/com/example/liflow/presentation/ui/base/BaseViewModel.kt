package com.example.liflow.presentation.ui.base

import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

class BaseViewModel<NAVIGATOR>: ViewModel() {
    private lateinit var navigator: WeakReference<NAVIGATOR>

    fun setNavigator(navigator: NAVIGATOR) {
        this.navigator = WeakReference(navigator)
    }

    fun getNavigor(): NAVIGATOR? {
        return navigator.get()
    }
}