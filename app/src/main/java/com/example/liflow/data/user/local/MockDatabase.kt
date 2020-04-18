package com.example.liflow.data.user.local

import com.example.liflow.data.user.local.model.*

class MockDatabase {
    companion object {
        val mockUserData = listOf(
            User(
                id = 1,
                username = "a",
                password = "p",
                firstname = "Mickael",
                lastname = "Au",
                description = "This is Mickael description",
                age = 23,
                totalClap =  300
            ),
            User(
                id = 2,
                username = "johndoe",
                password = "pwd",
                firstname = "John",
                lastname = "Doe",
                description = "This is John description",
                age = 32,
                totalClap =  22
            )
        )

        val mockUserSession = listOf(
            UserSession(
                id = 1,
                userId = 1,
                token = "fOlmNZnpfP"
            ),
            UserSession(
                id = 2,
                userId = 2,
                token = "#fnf))ge"
            )
        )

        val mockFollowingUser = listOf(
            FollowingUser(
                userId = 1,
                followingUserId = 2
            ),
            FollowingUser(
                userId = 2,
                followingUserId = 1
            )
        )

        val mockPostData = listOf(
            Post(
                id = 1,
                authorId = 1,
                title = "This is a title",
                description = "This is a description",
                solution = "This is a solution",
                url = null,
                category = "COUPLE"
            ),
            Post(
                id = 1,
                authorId = 2,
                title = "This is a title",
                description = "This is a description",
                solution = "This is a solution",
                url = null,
                category = "COUPLE"
            )
        )

        val mockLikedPost = listOf(
            LikedPost(
                userId = 1,
                postId = 1
            )
        )
    }
}