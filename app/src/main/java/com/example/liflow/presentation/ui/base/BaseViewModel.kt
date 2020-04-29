package com.example.liflow.presentation.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.liflow.presentation.models.ErrorHandler
import java.lang.ref.WeakReference


abstract class BaseViewModel<NAVIGATOR>: ViewModel() {
    protected var _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    protected var _errorHandler: MutableLiveData<ErrorHandler> = MutableLiveData()
    val errorHandler: LiveData<ErrorHandler> = _errorHandler

    private lateinit var _navigator: WeakReference<NAVIGATOR>

    fun setNavigator(navigator: NAVIGATOR) {
        this._navigator = WeakReference(navigator)
    }

    fun getNavigator(): NAVIGATOR? {
        return this._navigator.get()
    }
}