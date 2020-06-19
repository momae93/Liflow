package com.example.liflow.domain.category

import com.example.liflow.domain.category.usecases.GetAllCategory
import io.reactivex.rxjava3.observers.DisposableObserver

interface ICategoryDomain {
    fun getAllCategory(observer: DisposableObserver<GetAllCategory.Response>, params: GetAllCategory.Params)
}