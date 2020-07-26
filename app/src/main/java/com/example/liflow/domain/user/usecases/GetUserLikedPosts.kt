package com.example.liflow.domain.user.usecases

import com.example.liflow.domain.AbstractUseCase
import com.example.liflow.domain.user.IUserRepository
import com.example.liflow.presentation.models.SchedulerProvider
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetUserLikedPosts:
    AbstractUseCase<GetUserLikedPosts.Response, GetUserLikedPosts.Params> {
    private var userRepository: IUserRepository

    override fun buildUseCaseObservable(params: Params): Observable<Response> {
        return userRepository.getUserLikedPosts(params)
    }

    @Inject
    constructor(userRepository: IUserRepository, schedulerProvider: SchedulerProvider): super(schedulerProvider) {
        this.userRepository = userRepository
    }

    class Params constructor(
    )

    class Response constructor(
        val list: List<LikedPost>
    )

    class LikedPost constructor(
        val authorId: Int,
        val postId: Int,
        val categoryId: Int,
        val categoryName: String,
        val title: String,
        val pictureUrl: String?,
        val alreadyLiked: Boolean
    )
}