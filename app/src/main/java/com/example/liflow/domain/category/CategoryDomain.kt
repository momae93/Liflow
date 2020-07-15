package com.example.liflow.domain.category

import com.example.liflow.domain.category.usecases.GetAllCategory
import com.example.liflow.domain.category.usecases.GetCategoryDetails
import com.example.liflow.domain.category.usecases.GetCategoryPosts
import io.reactivex.rxjava3.observers.DisposableObserver
import javax.inject.Inject

class CategoryDomain: ICategoryDomain {
    private var getAllCategoryUC: GetAllCategory
    private var getCategoryDetailsUC: GetCategoryDetails
    private var getCategoryPostsUC: GetCategoryPosts

    @Inject
    constructor(
        getAllCategory: GetAllCategory ,
        getCategoryDetails: GetCategoryDetails,
        getCategoryPosts: GetCategoryPosts
    ) {
        this.getAllCategoryUC = getAllCategory
        this.getCategoryDetailsUC = getCategoryDetails
        this.getCategoryPostsUC = getCategoryPosts
    }

    override fun getAllCategory(
        observer: DisposableObserver<GetAllCategory.Response>,
        params: GetAllCategory.Params
    ) {
        getAllCategoryUC.execute(observer, params)
    }

    override fun getCategoryDetails(
        observer: DisposableObserver<GetCategoryDetails.Response>,
        params: GetCategoryDetails.Params
    ) {
        getCategoryDetailsUC.execute(observer, params)
    }

    override fun getCategoryPosts(
        observer: DisposableObserver<GetCategoryPosts.Response>,
        params: GetCategoryPosts.Params
    ) {
        getCategoryPostsUC.execute(observer, params)
    }
}