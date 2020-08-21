package com.example.liflow.presentation.models.bindingAdapters

import android.widget.EditText
import androidx.databinding.BindingAdapter

@BindingAdapter("cursorPosition")
fun bindCursorPosition(editText: EditText, event: Event<Int>?) {
    event?.getContentIfNotHandled()?.let { editText.setSelection(it) }
}