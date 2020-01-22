package com.example.liflow.ui.world

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WorldViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "World page in progress ..."
    }
    val text: LiveData<String> = _text
}