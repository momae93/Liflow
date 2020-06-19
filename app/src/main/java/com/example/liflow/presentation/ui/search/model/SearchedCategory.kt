package com.example.liflow.presentation.ui.search.model

import com.example.liflow.domain.category.usecases.GetAllCategory

class SearchedCategory(
    val categoryId: Int,
    val name: String,
    val pictureUrl: String?,
    val alreadyLiked: Boolean
) {
    companion object {
        fun map(responseData: GetAllCategory.Category): SearchedCategory {
            return SearchedCategory(
                categoryId = responseData.categoryId,
                name = responseData.name,
                pictureUrl = responseData.pictureUrl,
                alreadyLiked = responseData.alreadyLiked
            )
        }
    }
}