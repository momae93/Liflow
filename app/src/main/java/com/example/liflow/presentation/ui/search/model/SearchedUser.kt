package com.example.liflow.presentation.ui.search.model

import com.example.liflow.domain.user.usecases.GetSearchedUsers

class SearchedUser(
    val userId: Int,
    val fullname: String,
    val pictureUrl: String?,
    val totalPostPublished: String,
    val alreadyLiked: Boolean
) {
    companion object {
        fun map(responseData: GetSearchedUsers.User): SearchedUser {
            return SearchedUser(
                userId = responseData.userId,
                fullname = "${responseData.lastname} ${responseData.firstname}",
                pictureUrl = responseData.pictureUrl,
                totalPostPublished = "${responseData.totalPostPublished} posts published",
                alreadyLiked = responseData.alreadyLiked
            )
        }
    }
}