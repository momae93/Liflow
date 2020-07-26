package com.example.liflow.presentation.ui.like.model

import com.example.liflow.domain.user.usecases.GetLikedUsers

class LikedUser(
    val userId: Int,
    val fullname: String,
    val pictureUrl: String?,
    val alreadyLiked: Boolean,
    val totalNewPosts: String
) {
    companion object {
        fun map(responseData: GetLikedUsers.User): LikedUser {
            return LikedUser(
                userId = responseData.userId,
                fullname = "${responseData.lastname} ${responseData.firstname}",
                pictureUrl = responseData.pictureUrl,
                alreadyLiked = responseData.alreadyLiked,
                totalNewPosts = "${responseData.totalNewPosts} new posts"
            )
        }
    }
}