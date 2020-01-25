package com.example.liflow.presentation.ui.world

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WorldViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "World page in progress ..."
    }

    private fun test() {
    }
    val text: LiveData<String> = _text
}