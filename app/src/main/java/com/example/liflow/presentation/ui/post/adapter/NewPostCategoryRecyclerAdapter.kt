package com.example.liflow.presentation.ui.post.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.liflow.databinding.ItemNewPostCategoryBinding
import com.example.liflow.presentation.ui.post.model.Category

class NewPostCategoryRecyclerAdapter(
    private var list: List<Category> = listOf(),
    private val onCategoryListener: OnCategoryListener): RecyclerView.Adapter<NewPostCategoryViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewPostCategoryViewHolder {
        val inflater = LayoutInflater
            .from(parent.context)
        val itemNewPostCategoryBinding =
            ItemNewPostCategoryBinding.inflate(inflater, parent, false)
        context = parent.context

        return NewPostCategoryViewHolder(itemNewPostCategoryBinding, onCategoryListener)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: NewPostCategoryViewHolder, position: Int) {
        list[position].let { postThumbnail ->
            holder.bind(postThumbnail)
        }
    }

    interface OnCategoryListener {
        fun onCategoryClick(categoryId: Int)
    }
}