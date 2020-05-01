package com.example.liflow.presentation.ui.profile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.liflow.databinding.ItemProfilePostThumbnailBinding
import com.example.liflow.presentation.ui.profile.model.PostThumbnail

class PostRecyclerAdapter(private val list: List<PostThumbnail>,
                          private val onPostListener: OnPostListener): RecyclerView.Adapter<PostViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater
            .from(parent.context)
        val itemProfilePostThumbnailBinding = ItemProfilePostThumbnailBinding.inflate(inflater, parent, false)
        context = parent.context

        return PostViewHolder(itemProfilePostThumbnailBinding, onPostListener)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        list[position].let { postThumbnail ->
            holder.bind(postThumbnail)
        }
    }

    interface OnPostListener {
        fun onPostThumbnailClick(postId: Int)
    }
}