package com.example.liflow.presentation.ui.category.model

import com.example.liflow.domain.category.usecases.GetCategoryDetails

class CategoryDetails(
    val postId: Int,
    val name: String,
    val description: String,
    val pictureUrl: String?,
    val authors: List<UserThumbnail>,
    val alreadyLiked: Boolean
) {
    class User(
        val id: Int,
        val name: String,
        val pictureUrl: String?
    )

    companion object {
        fun map(responseData: GetCategoryDetails.Response): CategoryDetails {
            val authors = responseData.authors.map {
                UserThumbnail(
                    userId = it.userId,
                    fullname = "${it.firstname} ${it.lastname[0]}.",
                    pictureUrl = it.pictureUrl
                )
            }
            return CategoryDetails(
                postId = responseData.postId,
                name = responseData.name,
                description = responseData.description,
                pictureUrl = responseData.pictureUrl,
                authors = authors,
                alreadyLiked = responseData.alreadyLiked
            )
        }
    }
}