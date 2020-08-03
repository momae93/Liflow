package com.example.liflow.domain.user.usecases

import com.example.liflow.domain.AbstractUseCase
import com.example.liflow.domain.user.IUserRepository
import com.example.liflow.presentation.models.SchedulerProvider
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class PostUser:
    AbstractUseCase<String, PostUser.Params> {
    private var userRepository: IUserRepository

    override fun buildUseCaseObservable(params: Params): Observable<String> {
        return userRepository.postUser(params)
    }

    @Inject
    constructor(userRepository: IUserRepository, schedulerProvider: SchedulerProvider): super(schedulerProvider) {
        this.userRepository = userRepository
    }

    class Params constructor(
        val email: String,
        val password: String,
        val firstName: String,
        val lastName: String,
        val isMale: Boolean,
        val birthDate: String
    )
}