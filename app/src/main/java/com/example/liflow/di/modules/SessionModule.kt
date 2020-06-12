package com.example.liflow.di.modules

import com.example.liflow.domain.session.SessionDomain
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class SessionModule {
    @Provides
    @Named("sessionToken")
    fun provideSessionTokenProvider(sessionDomain: SessionDomain): String? {
        return sessionDomain.getSessionToken().sessionToken
    }
}