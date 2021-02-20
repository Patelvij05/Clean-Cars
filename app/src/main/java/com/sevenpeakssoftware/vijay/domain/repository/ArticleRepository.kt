package com.sevenpeakssoftware.vijay.domain.repository

import com.sevenpeakssoftware.vijay.data.source.local.entity.CarsArticle
import com.sevenpeakssoftware.vijay.domain.model.Articles
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

/**
 * To make an interaction between [ArticlesRepositoryImpl] & [GetArticleInteractor]
 * */
interface ArticleRepository {

    fun getArticles(): Single<Articles>

    fun addArticle(article: List<CarsArticle>)

    fun updateArticle(article: CarsArticle)

    fun loadAll(): Flowable<List<CarsArticle>>

}