package com.example.liflow.data.user.local.model

data class User(
    val id: Int,
    val username: String,
    val password: String,
    val firstname: String,
    val lastname: String,
    val pictureUrl: String?,
    val description: String,
    val age: Int,
    val isMale: Boolean,
    val totalClap: Int
)