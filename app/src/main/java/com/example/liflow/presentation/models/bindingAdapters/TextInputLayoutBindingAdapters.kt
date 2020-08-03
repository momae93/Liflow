package com.example.liflow.presentation.models.bindingAdapters

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:errorText")
fun loadImage(view: TextInputLayout, errorMessage: String?) {
    view.error = errorMessage
}
