package com.example.liflow.data.user.local

import com.example.liflow.data.user.local.model.*

class MockUserDatabase {
    companion object {
        val mockUserData = listOf(
            User(
                id = 1,
                username = "a",
                password = "p",
                firstname = "Mickael",
                lastname = "Au",
                description = "This is Mickael description",
                pictureUrl = null,
                age = 23,
                totalClap =  300,
                isMale = true
            ),
            User(
                id = 2,
                username = "johndoe",
                password = "pwd",
                firstname = "John",
                lastname = "Doe",
                description = "This is John description",
                pictureUrl = null,
                age = 32,
                totalClap =  22,
                isMale = true
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
    }
}