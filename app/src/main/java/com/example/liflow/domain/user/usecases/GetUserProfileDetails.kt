package com.example.liflow.domain.user.usecases

import com.example.liflow.di.scope.ActivityScope
import com.example.liflow.domain.AbstractUseCase
import com.example.liflow.domain.user.IUserRepository
import com.example.liflow.presentation.models.SchedulerProvider
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetUserProfileDetails: AbstractUseCase<GetUserProfileDetails.Response, GetUserProfileDetails.Params> {
    private var userRepository: IUserRepository

    override fun buildUseCaseObservable(params: Params): Observable<Response> {
        return userRepository.getUserProfileDetails(params)
    }

    @Inject
    constructor(userRepository: IUserRepository, schedulerProvider: SchedulerProvider): super(schedulerProvider) {
        this.userRepository = userRepository
    }

    class Params constructor(
    )
    class Response constructor(
        val firstname: String,
        val lastname: String,
        val description: String,
        val age: Int,
        val isMale: Boolean,
        val totalFollower: Int,
        val totalFollowing: Int,
        val totalClap: Int,
        val totalPostLiked: Int,
        val totalPostWritten: Int
    )
}