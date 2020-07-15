package com.example.liflow.domain.category

import com.example.liflow.domain.category.usecases.GetAllCategory
import com.example.liflow.domain.category.usecases.GetCategoryDetails
import com.example.liflow.domain.category.usecases.GetCategoryPosts
import io.reactivex.rxjava3.observers.DisposableObserver

interface ICategoryDomain {
    fun getAllCategory(observer: DisposableObserver<GetAllCategory.Response>, params: GetAllCategory.Params)
    fun getCategoryDetails(observer: DisposableObserver<GetCategoryDetails.Response>, params: GetCategoryDetails.Params)
    fun getCategoryPosts(observer: DisposableObserver<GetCategoryPosts.Response>, params: GetCategoryPosts.Params)
}