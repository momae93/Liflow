package com.example.liflow.domain.session.usecases

import com.example.liflow.domain.AbstractUseCase
import com.example.liflow.domain.session.ISessionRepository
import com.example.liflow.presentation.models.SchedulerProvider
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetSessionToken {
    private var sessionRepository: ISessionRepository

    @Inject
    constructor(sessionRepository: ISessionRepository) {
        this.sessionRepository = sessionRepository
    }

    fun execute(): Response {
        return sessionRepository.getSessionToken(Params())
    }

    class Params

    class Response constructor(
        val sessionToken: String?
    )
}