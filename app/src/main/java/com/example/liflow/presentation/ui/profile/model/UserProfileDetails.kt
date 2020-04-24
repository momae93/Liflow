package com.example.liflow.presentation.ui.profile.model

import com.example.liflow.domain.user.usecases.GetUserProfileDetails

class UserProfileDetails(
    val fullname: String,
    val description: String,
    val age: String,
    val gender: String,
    val totalFollower: String,
    val totalFollowing: String,
    val totalClap: String,
    val totalPostLiked: String,
    val totalPostWritten: String
) {
    companion object {
        private fun getPostPlural(postNb: Int): String {
            return if (postNb > 1) "posts" else "post"
        }

        fun map(responseData: GetUserProfileDetails.Response): UserProfileDetails {
            val gender = if (responseData.isMale) "Male" else "Female"
            val totalPostLiked = "${responseData.totalPostLiked} ${getPostPlural(responseData.totalPostLiked)} liked"
            val totalPostWritten = "${responseData.totalPostWritten} ${getPostPlural(responseData.totalPostWritten)} written"

            return UserProfileDetails(
                fullname = "${responseData.lastname} ${responseData.firstname}",
                description = responseData.description,
                age = "${responseData.age} y.o",
                gender = gender,
                totalFollower = "${responseData.totalFollower}",
                totalFollowing = "${responseData.totalFollowing}",
                totalClap = "${responseData.totalClap}",
                totalPostLiked = totalPostLiked,
                totalPostWritten = totalPostWritten
            )
        }
    }
}