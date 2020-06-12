package com.example.liflow.domain.session.usecases

import com.example.liflow.domain.AbstractUseCase
import com.example.liflow.domain.session.ISessionRepository
import com.example.liflow.presentation.models.SchedulerProvider
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class SetSessionToken:
    AbstractUseCase<SetSessionToken.Response, SetSessionToken.Params> {
    private var sessionRepository: ISessionRepository

    override fun buildUseCaseObservable(params: Params): Observable<Response> {
        return sessionRepository.setSessionToken(params)
    }

    @Inject
    constructor(sessionRepository: ISessionRepository, schedulerProvider: SchedulerProvider): super(schedulerProvider) {
        this.sessionRepository = sessionRepository
    }

    class Params constructor(
        val sessionToken: String
    )

    class Response
}