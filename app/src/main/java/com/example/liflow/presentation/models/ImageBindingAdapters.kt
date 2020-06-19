package com.example.liflow.presentation.models

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("imageViewBindingAdapter")
fun loadImage(view: ImageView, url: String?) {
    url?.let {
        Glide.with(view)
            .load(url)
            .into(view)
    }
}

@BindingAdapter("imageViewBindingAdapter")
fun loadImage(view: ImageView, resource: Int) {
    view.setImageResource(resource)
}
