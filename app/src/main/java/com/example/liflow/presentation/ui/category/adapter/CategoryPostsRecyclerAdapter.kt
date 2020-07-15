package com.example.liflow.presentation.ui.category.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.liflow.databinding.ItemCategoryPostThumbnailBinding
import com.example.liflow.presentation.ui.category.model.PostThumbnail

class CategoryPostsRecyclerAdapter(val list: List<PostThumbnail>, private val postListener: OnPostListener): RecyclerView.Adapter<CategoryPostsViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryPostsViewHolder {
        val inflater = LayoutInflater
            .from(parent.context)
        val itemCategoryPostBinding = ItemCategoryPostThumbnailBinding.inflate(inflater, parent, false)
        context = parent.context

        return CategoryPostsViewHolder(itemCategoryPostBinding, postListener)
    }

    override fun getItemCount():Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: CategoryPostsViewHolder, position: Int) {
        list[position].let { post ->
            holder.bind(post)
        }
    }

    interface OnPostListener {
        fun onPostClick(postId: Int)
    }
}