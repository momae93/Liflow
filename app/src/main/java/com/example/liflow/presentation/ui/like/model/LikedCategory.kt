package com.example.liflow.presentation.ui.like.model

import com.example.liflow.domain.user.usecases.GetLikedCategories

class LikedCategory(
    val categoryId: Int,
    val name: String,
    val pictureUrl: String?,
    val alreadyLiked: Boolean
) {
    companion object {
        fun map(responseData: GetLikedCategories.Category): LikedCategory {
            return LikedCategory(
                categoryId = responseData.categoryId,
                name = responseData.name,
                pictureUrl = responseData.pictureUrl,
                alreadyLiked = responseData.alreadyLiked
            )
        }
    }
}