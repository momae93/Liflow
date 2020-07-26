package com.example.liflow.presentation.ui.like.model

import com.example.liflow.domain.user.usecases.GetUserLikedPosts

class LikedPost(
    val authorId: Int,
    val postId: Int,
    val categoryName: String,
    val title: String,
    val pictureUrl: String?,
    val alreadyLiked: Boolean
) {
    companion object {
        fun map(responseData: GetUserLikedPosts.LikedPost): LikedPost {
            return LikedPost(
                authorId = responseData.authorId,
                postId = responseData.postId,
                categoryName = responseData.categoryName,
                title = responseData.title,
                pictureUrl = responseData.pictureUrl,
                alreadyLiked = responseData.alreadyLiked
            )
        }
    }
}