package com.example.liflow.domain.user.usecases

import com.example.liflow.domain.AbstractUseCase
import com.example.liflow.domain.user.IUserRepository
import com.example.liflow.presentation.models.SchedulerProvider
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetSearchedUsers:
    AbstractUseCase<GetSearchedUsers.Response, GetSearchedUsers.Params> {
    private var userRepository: IUserRepository

    override fun buildUseCaseObservable(params: Params): Observable<Response> {
        return userRepository.getSearchedUsers(params)
    }

    @Inject
    constructor(userRepository: IUserRepository, schedulerProvider: SchedulerProvider): super(schedulerProvider) {
        this.userRepository = userRepository
    }

    class Params constructor(val searchPattern: String)

    class Response constructor(
        val list: List<User>
    )

    class User constructor(
        val userId: Int,
        val firstname: String,
        val lastname: String,
        val totalPostPublished: Int,
        val pictureUrl: String?,
        val alreadyLiked: Boolean
    )
}