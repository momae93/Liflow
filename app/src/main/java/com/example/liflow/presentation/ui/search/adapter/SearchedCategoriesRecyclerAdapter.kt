package com.example.liflow.presentation.ui.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.liflow.databinding.ItemSearchCategoriesResultBinding
import com.example.liflow.presentation.ui.search.model.SearchedCategory

class SearchedCategoriesRecyclerAdapter(val list: List<SearchedCategory>, private val onCategoryListener: OnCategoryListener): RecyclerView.Adapter<SearchedCategoriesViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedCategoriesViewHolder {
        val inflater = LayoutInflater
            .from(parent.context)
        val itemSearchedCategoriesBinding = ItemSearchCategoriesResultBinding.inflate(inflater, parent, false)
        context = parent.context

        return SearchedCategoriesViewHolder(itemSearchedCategoriesBinding, onCategoryListener)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: SearchedCategoriesViewHolder, position: Int) {
        list[position].let { searchedCategories ->
            holder.bind(searchedCategories)
        }
    }

    interface OnCategoryListener {
        fun onCategoryClick(categoryId: Int)
    }
}