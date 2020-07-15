package com.example.liflow.data.category

import com.example.liflow.data.category.local.MockCategoryDatabase
import com.example.liflow.data.post.local.MockPostDatabase
import com.example.liflow.data.user.local.MockUserDatabase
import com.example.liflow.domain.category.ICategoryRepository
import com.example.liflow.domain.category.usecases.GetAllCategory
import com.example.liflow.domain.category.usecases.GetCategoryDetails
import com.example.liflow.domain.category.usecases.GetCategoryPosts
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

    override fun getCategoryDetails(params: GetCategoryDetails.Params): Observable<GetCategoryDetails.Response> {
        val sessionToken = MockUserDatabase.mockUserSession.find { it.token == sessionToken }
            ?: return Observable.error(Throwable("User token does not exists"))
        val user = MockUserDatabase.mockUserData.find { it.id == sessionToken.userId }
            ?: return Observable.error(Throwable("User that liked the post does not exists"))
        val category = MockCategoryDatabase.mockCategory.find { it.id == params.categoryId }
            ?: return Observable.error(Throwable("Category not found"))
        val alreadyLiked = MockCategoryDatabase.mockLikedCategory.find { it.userId == user.id } !== null
        val authors = MockUserDatabase.mockUserData.map {
            GetCategoryDetails.User(
                userId = it.id,
                firstname = it.firstname,
                lastname = it.lastname,
                pictureUrl = it.pictureUrl
            )
        }

        return Observable.just(GetCategoryDetails.Response(
            postId = category.id,
            name = category.name,
            description = category.description,
            pictureUrl = category.pictureUrl,
            authors = authors,
            alreadyLiked = alreadyLiked
        ))
    }

    override fun getCategoryPosts(params: GetCategoryPosts.Params): Observable<GetCategoryPosts.Response> {
        val category = MockCategoryDatabase.mockCategory.find { it.id == params.categoryId }
            ?: return Observable.error(Throwable("Category not found"))

        val posts = MockPostDatabase.mockPostData
            .filter { it.categoryId == params.categoryId }
            .map { p ->
            val user = MockUserDatabase.mockUserData.find { it.id == p.authorId }
                ?: return Observable.error(Throwable("User that liked the post does not exists"))
            GetCategoryPosts.Post(postId = p.id, category = category.name, title = p.title, pictureUrl = user.pictureUrl)
        }

        return Observable.just(GetCategoryPosts.Response(
            list = posts
        ))
    }
}