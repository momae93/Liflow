package com.example.liflow.di.modules

import com.example.liflow.domain.user.IUserDomain
import com.example.liflow.domain.user.UserDomain
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Provides
    @Singleton
    fun provideUserDomain(userDomain: UserDomain): IUserDomain {
        return userDomain
    }
}