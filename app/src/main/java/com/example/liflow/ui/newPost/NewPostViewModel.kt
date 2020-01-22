package com.example.liflow.ui.newPost

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewPostViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "New post page in progress ..."
    }
    val text: LiveData<String> = _text
}