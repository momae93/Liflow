package com.example.liflow.presentation.ui.post.model

import com.example.liflow.domain.post.usecases.GetRandomDailyPost
import java.util.*

class DailyRandomPostThumbnail(
    val uuid: UUID,
    val authorId: Int,
    val postId: Int,
    val category: String,
    val fullname: String,
    val title: String,
    val reason: String
) {
    companion object {
        fun map(responseData: GetRandomDailyPost.Response): DailyRandomPostThumbnail {
            return DailyRandomPostThumbnail(
                uuid = UUID.randomUUID(),
                postId = responseData.postId,
                authorId = responseData.authorId,
                category = responseData.category,
                fullname = "${responseData.lastname} ${responseData.firstname}",
                title = responseData.title,
                reason = responseData.reason
            )
        }
    }
}