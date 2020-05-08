package com.example.liflow.presentation.ui.post.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.liflow.databinding.ItemDailyRandomPostBinding
import com.example.liflow.databinding.ItemProfilePostThumbnailBinding
import com.example.liflow.presentation.ui.post.model.DailyRandomPostThumbnail

class DailyRandomPostRecyclerAdapter(
    private var list: List<DailyRandomPostThumbnail> = listOf(),
    private val onPostListener: OnPostListener): RecyclerView.Adapter<DailyRandomPostViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyRandomPostViewHolder {
        val inflater = LayoutInflater
            .from(parent.context)
        val itemProfilePostThumbnailBinding =
            ItemDailyRandomPostBinding.inflate(inflater, parent, false)
        context = parent.context

        return DailyRandomPostViewHolder(itemProfilePostThumbnailBinding, onPostListener)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: DailyRandomPostViewHolder, position: Int) {
        list[position].let { postThumbnail ->
            holder.bind(postThumbnail)
        }
    }

    fun updateList(newList: List<DailyRandomPostThumbnail>) {
        this.list = newList
        this.notifyDataSetChanged()
    }

    interface OnPostListener {
        fun onPostThumbnailClick(postId: Int)
    }
}