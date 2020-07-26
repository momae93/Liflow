package com.example.liflow.presentation.ui.profile.model

import com.example.liflow.domain.user.usecases.GetUserLikedPosts
import com.example.liflow.domain.user.usecases.GetUserWrittenPosts

class PostThumbnail(
    val id: Int,
    val title: String,
    val category: String,
    val pictureUrl: String?
) {
    companion object {
        fun map(responseData: GetUserWrittenPosts.Response): List<PostThumbnail> {
            return responseData.list.map {
                PostThumbnail(
                    id = it.id,
                    title = it.title,
                    category = it.category,
                    pictureUrl = it.pictureUrl
                )
            }
        }
    }
}