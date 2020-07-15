package com.example.liflow.presentation.ui.category.model

import com.example.liflow.domain.category.usecases.GetCategoryDetails

class UserThumbnail(
    val userId: Int,
    val fullname: String,
    val pictureUrl: String?
) {
    companion object {
        fun map(responseData: GetCategoryDetails.User): UserThumbnail {
            return UserThumbnail(
                userId = responseData.userId,
                fullname = "${responseData.lastname} ${responseData.firstname}",
                pictureUrl = responseData.pictureUrl
            )
        }
    }
}