package com.example.liflow.di.modules

import com.example.liflow.data.post.PostRepository
import com.example.liflow.data.user.UserRepository
import com.example.liflow.domain.post.IPostRepository
import com.example.liflow.domain.user.IUserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideUserRepository(userRepository: UserRepository): IUserRepository {
        return userRepository
    }

    @Provides
    @Singleton
    fun providePostRepository(postRepository: PostRepository): IPostRepository {
        return postRepository
    }
}