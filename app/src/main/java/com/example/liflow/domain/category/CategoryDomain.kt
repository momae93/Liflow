package com.example.liflow.domain.category

import com.example.liflow.domain.category.usecases.GetAllCategory
import io.reactivex.rxjava3.observers.DisposableObserver
import javax.inject.Inject

class CategoryDomain: ICategoryDomain {
    private var getAllCategoryUC: GetAllCategory

    @Inject
    constructor(
        getAllCategory: GetAllCategory
    ) {
        this.getAllCategoryUC = getAllCategory
    }

    override fun getAllCategory(
        observer: DisposableObserver<GetAllCategory.Response>,
        params: GetAllCategory.Params
    ) {
        getAllCategoryUC.execute(observer, params)
    }
}