package com.example.liflow.data.user.local.model

data class Post(
    val id: Int,
    val authorId: Int,
    val title: String,
    val description: String,
    val solution: String,
    val url: String?,
    val category: String
)