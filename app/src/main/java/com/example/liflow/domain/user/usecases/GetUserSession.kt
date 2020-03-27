package com.example.liflow.domain.user.usecases

import com.example.liflow.domain.AbstractUseCase
import com.example.liflow.domain.user.IUserRepository
import com.example.liflow.presentation.models.SchedulerProvider
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

class GetUserSession:
    AbstractUseCase<String, GetUserSession.Params> {

    private var userRepository: IUserRepository

    override fun buildUseCaseObservable(params: Params): Observable<String> {
        return userRepository.getUserSession(params)
    }

    @Inject
    constructor(userRepository: IUserRepository, schedulerProvider: SchedulerProvider): super(schedulerProvider) {
        this.userRepository = userRepository
    }

    class Params constructor(val username: String, val password: String)
}