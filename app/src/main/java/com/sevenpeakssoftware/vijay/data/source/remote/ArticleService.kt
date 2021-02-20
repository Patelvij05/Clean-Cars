package com.sevenpeakssoftware.vijay.data.source.remote

import com.sevenpeakssoftware.vijay.domain.model.Articles
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ArticleService {

    @GET("article/get_articles_list")
    fun getArticlesList(): Single<Articles>

}