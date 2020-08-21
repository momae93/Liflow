package com.example.liflow.presentation.models

import androidx.lifecycle.MutableLiveData

abstract class StepForm() {
    val valid: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }
    abstract fun validate(): Boolean
    open fun resetFields() {}
}