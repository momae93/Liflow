package com.example.liflow.domain.category.usecases

import com.example.liflow.domain.AbstractUseCase
import com.example.liflow.domain.category.ICategoryRepository
import com.example.liflow.presentation.models.SchedulerProvider
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetAllCategory: AbstractUseCase<GetAllCategory.Response, GetAllCategory.Params> {
    private var categoryRepository: ICategoryRepository

    override fun buildUseCaseObservable(params: Params): Observable<Response> {
        return categoryRepository.getAllCategory(params)
    }

    @Inject
    constructor(categoryRepository: ICategoryRepository, schedulerProvider: SchedulerProvider): super(schedulerProvider) {
        this.categoryRepository = categoryRepository
    }

    class Params

    class Response constructor(
        val list: List<Category>
    )

    class Category constructor(
        val categoryId: Int,
        val name: String,
        val pictureUrl: String?,
        val alreadyLiked: Boolean
    )
}