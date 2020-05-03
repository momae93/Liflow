package com.example.liflow.presentation.models

import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton

@BindingAdapter("imageViewBindingAdapter")
fun loadImage(view: ImageView, url: String?) {
    url?.let {
            Glide.with(view)
                .load(url)
                .into(view)
    }
}
