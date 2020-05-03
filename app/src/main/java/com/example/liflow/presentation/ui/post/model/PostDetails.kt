package com.example.liflow.presentation.ui.post.model

import com.example.liflow.R
import com.example.liflow.domain.post.usecases.GetPostDetails
import com.example.liflow.domain.post.usecases.PostClapsPost
import com.example.liflow.domain.post.usecases.PostLikePost

class PostDetails(
    val authorId: Int,
    val postId: Int,
    val fullname: String,
    val gender: String,
    val age: String,
    val title: String,
    val reason: String,
    val description: String,
    val totalClap: Int
) {
    companion object {
        fun map(responseData: GetPostDetails.Response): PostDetails {
            val gender = if (responseData.isMale) "Male" else "Female"

            return PostDetails(
                postId = responseData.postId,
                authorId = responseData.authorId,
                fullname = "${responseData.lastname} ${responseData.firstname}",
                gender = gender,
                title = responseData.title,
                reason = responseData.reason,
                description = responseData.description,
                totalClap = responseData.totalClap,
                age = "${responseData.age} y.o"
            )
        }

        fun map(responseData: PostLikePost.Response): PostDetails {
            val gender = if (responseData.isMale) "Male" else "Female"

            return PostDetails(
                postId = responseData.postId,
                authorId = responseData.authorId,
                fullname = "${responseData.lastname} ${responseData.firstname}",
                gender = gender,
                title = responseData.title,
                reason = responseData.reason,
                description = responseData.description,
                totalClap = responseData.totalClap,
                age = "${responseData.age} y.o"
            )
        }

        fun map(responseData: PostClapsPost.Response): PostDetails {
            val gender = if (responseData.isMale) "Male" else "Female"

            return PostDetails(
                postId = responseData.postId,
                authorId = responseData.authorId,
                fullname = "${responseData.lastname} ${responseData.firstname}",
                gender = gender,
                title = responseData.title,
                reason = responseData.reason,
                description = responseData.description,
                totalClap = responseData.totalClap,
                age = "${responseData.age} y.o"
            )
        }
    }
}