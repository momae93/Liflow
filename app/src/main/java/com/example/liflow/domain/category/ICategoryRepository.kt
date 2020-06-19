package com.example.liflow.domain.category

import com.example.liflow.domain.category.usecases.GetAllCategory
import io.reactivex.rxjava3.core.Observable

interface ICategoryRepository {
    fun getAllCategory(params: GetAllCategory.Params): Observable<GetAllCategory.Response>
}