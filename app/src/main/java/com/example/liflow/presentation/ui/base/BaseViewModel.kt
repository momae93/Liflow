package com.example.liflow.presentation.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.liflow.presentation.models.ErrorHandler

abstract class BaseViewModel: ViewModel() {
    protected var _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    protected var _errorHandler: MutableLiveData<ErrorHandler> = MutableLiveData()
    val errorHandler: LiveData<ErrorHandler> = _errorHandler
}