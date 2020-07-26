package com.example.liflow.domain.user.usecases

import com.example.liflow.domain.AbstractUseCase
import com.example.liflow.domain.user.IUserRepository
import com.example.liflow.presentation.models.SchedulerProvider
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetProfileDetails: AbstractUseCase<GetProfileDetails.Response, GetProfileDetails.Params> {
    private var userRepository: IUserRepository

    override fun buildUseCaseObservable(params: Params): Observable<Response> {
        return userRepository.getProfileDetails(params)
    }

    @Inject
    constructor(userRepository: IUserRepository, schedulerProvider: SchedulerProvider): super(schedulerProvider) {
        this.userRepository = userRepository
    }

    class Params constructor(
        val userId: Int
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
        val totalBadges: Int,
        val totalPostWritten: Int
    )
}