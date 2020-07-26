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
    val totalBadges: String,
    val totalPostWritten: String
) {
    companion object {
        private fun adaptTextWhenPlural(postNb: Int): String {
            return if (postNb > 1) "posts" else "post"
        }

        fun map(responseData: GetUserProfileDetails.Response): UserProfileDetails {
            val gender = if (responseData.isMale) "Male" else "Female"
            val totalBadges = "${responseData.totalBadges} badges"
            val totalPostWritten = "${responseData.totalPostWritten} ${adaptTextWhenPlural(responseData.totalPostWritten)} written"

            return UserProfileDetails(
                fullname = "${responseData.lastname} ${responseData.firstname}",
                description = responseData.description,
                age = "${responseData.age} y.o",
                gender = gender,
                totalFollower = "${responseData.totalFollower}",
                totalFollowing = "${responseData.totalFollowing}",
                totalClap = "${responseData.totalClap}",
                totalBadges = totalBadges,
                totalPostWritten = totalPostWritten
            )
        }
    }
}