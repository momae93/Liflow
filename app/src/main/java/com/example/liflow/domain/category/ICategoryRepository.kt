package com.example.liflow.domain.category

import com.example.liflow.domain.category.usecases.GetAllCategory
import com.example.liflow.domain.category.usecases.GetCategoryDetails
import com.example.liflow.domain.category.usecases.GetCategoryPosts
import io.reactivex.rxjava3.core.Observable

interface ICategoryRepository {
    fun getAllCategory(params: GetAllCategory.Params): Observable<GetAllCategory.Response>
    fun getCategoryDetails(params: GetCategoryDetails.Params): Observable<GetCategoryDetails.Response>
    fun getCategoryPosts(params: GetCategoryPosts.Params): Observable<GetCategoryPosts.Response>
}