package com.example.liflow.presentation.models.bindingAdapters

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

@BindingAdapter("app:errorText")
fun loadImage(view: TextInputLayout, errorMessage: String?) {
    view.error = errorMessage
}

