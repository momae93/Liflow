package com.example.liflow.di.modules

import com.example.liflow.domain.category.CategoryDomain
import com.example.liflow.domain.category.ICategoryDomain
import com.example.liflow.domain.post.IPostDomain
import com.example.liflow.domain.post.PostDomain
import com.example.liflow.domain.session.ISessionDomain
import com.example.liflow.domain.session.SessionDomain
import com.example.liflow.domain.user.IUserDomain
import com.example.liflow.domain.user.UserDomain
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideUserDomain(userDomain: UserDomain): IUserDomain {
        return userDomain
    }

    @Provides
    fun providePostDomain(postDomain: PostDomain): IPostDomain {
        return postDomain
    }

    @Provides
    fun provideSessionDomain(sessionDomain: SessionDomain): ISessionDomain {
        return sessionDomain
    }

    @Provides
    fun provideCategoryDomain(categoryDomain: CategoryDomain): ICategoryDomain {
        return categoryDomain
    }
}