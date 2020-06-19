package com.example.liflow.di.modules

import com.example.liflow.data.category.CategoryRepository
import com.example.liflow.data.post.PostRepository
import com.example.liflow.data.session.SessionRepository
import com.example.liflow.data.user.UserRepository
import com.example.liflow.di.scope.ActivityScope
import com.example.liflow.domain.category.ICategoryRepository
import com.example.liflow.domain.post.IPostRepository
import com.example.liflow.domain.session.ISessionRepository
import com.example.liflow.domain.user.IUserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    fun provideUserRepository(userRepository: UserRepository): IUserRepository {
        return userRepository
    }

    @Provides
    fun providePostRepository(postRepository: PostRepository): IPostRepository {
        return postRepository
    }

    @Provides
    fun provideSessionRepository(sessionRepository: SessionRepository): ISessionRepository {
        return sessionRepository
    }

    @Provides
    fun provideCategoryDomain(categoryRepository: CategoryRepository): ICategoryRepository {
        return categoryRepository
    }
}