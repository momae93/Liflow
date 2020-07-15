package com.example.liflow.domain.category.usecases

import com.example.liflow.domain.AbstractUseCase
import com.example.liflow.domain.category.ICategoryRepository
import com.example.liflow.presentation.models.SchedulerProvider
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetCategoryDetails: AbstractUseCase<GetCategoryDetails.Response, GetCategoryDetails.Params> {
    private var categoryRepository: ICategoryRepository

    override fun buildUseCaseObservable(params: Params): Observable<Response> {
        return categoryRepository.getCategoryDetails(params)
    }

    @Inject
    constructor(categoryRepository: ICategoryRepository, schedulerProvider: SchedulerProvider): super(schedulerProvider) {
        this.categoryRepository = categoryRepository
    }

    class Params(val categoryId: Int)

    class Response constructor(
        val postId: Int,
        val name: String,
        val description: String,
        val pictureUrl: String?,
        val authors: List<User>,
        val alreadyLiked: Boolean
    )

    class User constructor(
        val userId: Int,
        val firstname: String,
        val lastname: String,
        val pictureUrl: String?
    )
}