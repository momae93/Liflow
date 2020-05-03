package com.example.liflow.data.post.local.model

data class PostDetails(
    val postId: Int,
    val title: String,
    val reason: String,
    val description: String,
    val totalClap: Int,
    val alreadyLiked: Boolean
)
