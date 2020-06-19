package com.example.liflow.data.category

import com.example.liflow.data.category.local.MockCategoryDatabase
import com.example.liflow.data.user.local.MockUserDatabase
import com.example.liflow.domain.category.ICategoryRepository
import com.example.liflow.domain.category.usecases.GetAllCategory
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Named

class CategoryRepository @Inject constructor() : ICategoryRepository {
    @Inject
    @JvmField
    @Named("sessionToken")
    internal var sessionToken: String? = null

    override fun getAllCategory(params: GetAllCategory.Params): Observable<GetAllCategory.Response> {
        val sessionToken = MockUserDatabase.mockUserSession.find { it.token == sessionToken }
            ?: return Observable.error(Throwable("User token does not exists"))
        val user = MockUserDatabase.mockUserData.find { it.id == sessionToken.userId }
            ?: return Observable.error(Throwable("User that liked the post does not exists"))
        val categories = MockCategoryDatabase.mockCategory.map { category ->
            val isAlreadyLiked = MockCategoryDatabase.mockLikedCategory
                .find { it.categoryId == category.id && it.userId == user.id}
            GetAllCategory.Category(
                categoryId = category.id,
                name = category.name,
                pictureUrl = category.pictureUrl,
                alreadyLiked = isAlreadyLiked !== null
            )
        }

        return Observable.just(GetAllCategory.Response(
            list = categories
        ))
    }
}