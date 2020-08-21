package com.example.liflow.domain.post.usecases

import com.example.liflow.domain.AbstractUseCase
import com.example.liflow.domain.post.IPostRepository
import com.example.liflow.presentation.models.SchedulerProvider
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class PostNewPost: AbstractUseCase<PostNewPost.Response, PostNewPost.Params> {
    private var postRepository: IPostRepository

    override fun buildUseCaseObservable(params: Params): Observable<Response> {
        return postRepository.postNewPost(params)
    }

    @Inject
    constructor(postRepository: IPostRepository, schedulerProvider: SchedulerProvider): super(schedulerProvider) {
        this.postRepository = postRepository
    }

    class Params constructor(
        val categoryId: Int,
        val title: String,
        val description: String,
        val solution: String
    )
    class Response constructor(
        val id: Int,
        val categoryId: Int,
        val title: String,
        val description: String,
        val solution: String
    )
}