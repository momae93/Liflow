package com.example.liflow.presentation.models

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

abstract class FormField<T>(private val errorMessage: String? = "") {
    val fieldValue: MutableLiveData<T> = MutableLiveData()
    val valid: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = true }
    val error = MediatorLiveData<String?>().apply {
        addSource(valid) { value = if (valid.value == true) null else errorMessage }
    }

    open fun validate() {}
}