package com.example.liflow.data.category.local

import com.example.liflow.data.category.local.model.LikedCategory
import com.example.liflow.data.category.local.model.Category

class MockCategoryDatabase {
    companion object {
        val mockCategory = listOf(
            Category(
                id = 1,
                name = "Couple",
                description = "This is couple category description",
                pictureUrl = null
            ),
            Category(
                id = 2,
                name = "Friends",
                description = "This is friends category description",
                pictureUrl = null
            )
        )

        val mockLikedCategory = mutableListOf(
            LikedCategory(
                userId = 1,
                categoryId = 2
            )
        )
    }
}