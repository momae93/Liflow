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
        val sessionToken: String
    )

    class Response constructor(
        val list: List<LikedPost>
    )

    class LikedPost constructor(
        val id: Int,
        val title: String,
        val category: String,
        val pictureUrl: String?
    )
}