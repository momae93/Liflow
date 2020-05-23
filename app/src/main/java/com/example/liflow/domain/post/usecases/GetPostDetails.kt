package com.example.liflow.domain.post.usecases

import com.example.liflow.domain.AbstractUseCase
import com.example.liflow.domain.post.IPostRepository
import com.example.liflow.presentation.models.SchedulerProvider
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetPostDetails: AbstractUseCase<GetPostDetails.Response, GetPostDetails.Params> {
    private var postRepository: IPostRepository

    override fun buildUseCaseObservable(params: Params): Observable<Response> {
        return postRepository.getPostDetails(params)
    }

    @Inject
    constructor(postRepository: IPostRepository, schedulerProvider: SchedulerProvider): super(schedulerProvider) {
        this.postRepository = postRepository
    }

    class Params constructor(
        val postId: Int
    )
    class Response constructor(
        val postId: Int,
        val authorId: Int,
        val firstname: String,
        val lastname: String,
        val isMale: Boolean,
        val age: Int,
        val title: String,
        val reason: String,
        val description: String,
        val totalClap: Int,
        val alreadyLiked: Boolean
    )
}