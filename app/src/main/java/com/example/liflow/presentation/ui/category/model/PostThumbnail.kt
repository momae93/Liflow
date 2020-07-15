package com.example.liflow.presentation.ui.category.model

import com.example.liflow.domain.category.usecases.GetCategoryPosts

class PostThumbnail(
    val postId: Int,
    val title: String,
    val category: String,
    val pictureUrl: String?
) {
    companion object {
        fun map(responseData: GetCategoryPosts.Post): PostThumbnail {
            return PostThumbnail(
                postId = responseData.postId,
                title = responseData.title,
                category = responseData.category,
                pictureUrl = responseData.pictureUrl
            )
        }
    }
}