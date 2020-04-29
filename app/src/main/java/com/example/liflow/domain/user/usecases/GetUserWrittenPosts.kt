package com.example.liflow.domain.user.usecases

import com.example.liflow.domain.AbstractUseCase
import com.example.liflow.domain.user.IUserRepository
import com.example.liflow.presentation.models.SchedulerProvider
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetUserWrittenPosts:
    AbstractUseCase<GetUserWrittenPosts.Response, GetUserWrittenPosts.Params> {
    private var userRepository: IUserRepository

    override fun buildUseCaseObservable(params: Params): Observable<Response> {
        return userRepository.getUserWrittenPosts(params)
    }

    @Inject
    constructor(userRepository: IUserRepository, schedulerProvider: SchedulerProvider): super(schedulerProvider) {
        this.userRepository = userRepository
    }

    class Params constructor(
        val sessionToken: String
    )

    class Response constructor(
        val list: List<WrittenPost>
    )

    class WrittenPost constructor(
        val id: Int,
        val title: String,
        val category: String,
        val pictureUrl: String?
    )
}