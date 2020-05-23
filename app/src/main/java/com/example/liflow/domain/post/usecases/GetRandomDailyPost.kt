package com.example.liflow.domain.post.usecases

import com.example.liflow.domain.AbstractUseCase
import com.example.liflow.domain.post.IPostRepository
import com.example.liflow.presentation.models.SchedulerProvider
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetRandomDailyPost: AbstractUseCase<GetRandomDailyPost.Response, GetRandomDailyPost.Params> {
    private var postRepository: IPostRepository

    override fun buildUseCaseObservable(params: Params): Observable<Response> {
        return postRepository.getRandomDailyPost(params)
    }

    @Inject
    constructor(postRepository: IPostRepository, schedulerProvider: SchedulerProvider): super(schedulerProvider) {
        this.postRepository = postRepository
    }

    class Params constructor(
    )

    class Response constructor(
        val postId: Int,
        val authorId: Int,
        val firstname: String,
        val lastname: String,
        val category: String,
        val title: String,
        val reason: String
    )
}