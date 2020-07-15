package com.example.liflow.data.post.local

import com.example.liflow.data.category.local.model.LikedCategory
import com.example.liflow.data.category.local.model.Category
import com.example.liflow.data.post.local.model.LikedPost
import com.example.liflow.data.post.local.model.Post
import com.example.liflow.data.post.local.model.PostDetails

class MockPostDatabase {
    companion object {
        val mockPostData = listOf(
            Post(
                id = 1,
                authorId = 1,
                categoryId = 1,
                title = "How to improve your relation",
                description = "This is a description",
                solution = "This is a solution",
                url = "https://picsum.photos/200",
                category = "COUPLE"
            ),
            Post(
                id = 2,
                authorId = 2,
                categoryId = 2,
                title = "How to be a better friend",
                description = "This is a description",
                solution = "This is a solution",
                url = "https://picsum.photos/200",
                category = "FRIENDSHIP"
            )
        )

        val mockLikedPost = mutableListOf(
            LikedPost(
                userId = 1,
                postId = 2
            )
        )

        val mockPostDetails = mutableListOf(
            PostDetails(
                postId = 1,
                title = "Title first post",
                reason = "Reason first post",
                description = "Description first post",
                totalClap = 10,
                alreadyLiked = true
            ),
            PostDetails(
                postId = 2,
                title = "Title second post",
                reason = "Reason second post",
                description = "Description second post",
                totalClap = 0,
                alreadyLiked = false
            )
        )
    }
}