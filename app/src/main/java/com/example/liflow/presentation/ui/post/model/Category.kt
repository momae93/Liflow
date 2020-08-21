package com.example.liflow.presentation.ui.post.model

import com.example.liflow.domain.category.usecases.GetAllCategory

class Category(
    val categoryId: Int,
    val name: String,
    val pictureUrl: String?
) {
    companion object {
        fun map(responseData: GetAllCategory.Category): Category {
            return Category(
                categoryId = responseData.categoryId,
                name = responseData.name,
                pictureUrl = responseData.pictureUrl
            )
        }
    }
}