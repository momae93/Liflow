package com.example.liflow.domain.category.usecases

import com.example.liflow.domain.AbstractUseCase
import com.example.liflow.domain.category.ICategoryRepository
import com.example.liflow.presentation.models.SchedulerProvider
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetCategoryPosts: AbstractUseCase<GetCategoryPosts.Response, GetCategoryPosts.Params> {
    private var categoryRepository: ICategoryRepository

    override fun buildUseCaseObservable(params: Params): Observable<Response> {
        return categoryRepository.getCategoryPosts(params)
    }

    @Inject
    constructor(categoryRepository: ICategoryRepository, schedulerProvider: SchedulerProvider): super(schedulerProvider) {
        this.categoryRepository = categoryRepository
    }

    class Params(val categoryId: Int)

    class Response constructor(
        val list: List<Post>
    )

    class Post constructor(
        val postId: Int,
        val title: String,
        val category: String,
        val pictureUrl: String?
    )
}